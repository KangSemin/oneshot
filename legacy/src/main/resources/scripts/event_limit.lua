-- 키 목록: KEYS[1]=카운터키, KEYS[2]=제한키, KEYS[3]=당첨자키
-- 인자 목록: ARGV[1]=제한수량, ARGV[2]=사용자ID

-- 현재 카운터 값 확인
-- local : 지역변수 선언
-- counter = nil, null → counter = 0
local counter = redis.call('GET', KEYS[1])
if not counter then
    counter = 0
end

-- 제한 수량 가져오기 (없으면 인자로 받은 값 사용)
local limit = redis.call('GET', KEYS[2])
if not limit then
    limit = ARGV[1]
    redis.call('SET', KEYS[2], limit)
end

-- 이미 참여했는지 확인
-- SISMEMBER = EXIST, 값이 존재 = 1, 값이 미존재 = 0
local participated = redis.call('SISMEMBER', KEYS[3], ARGV[2])
if participated == 1 then
    return -1  -- 이미 참여함
end

-- 제한 수량 확인
-- tonumber = 문자열을 숫자로 변환
if tonumber(counter) < tonumber(limit) then
    -- 카운터 증가
    local newPosition = redis.call('INCR', KEYS[1])

    -- 제한 수량 이내인지 다시 확인 (다른 스레드에 의한 증가 가능성)
    if tonumber(newPosition) <= tonumber(limit) then
        -- 당첨자 목록에 추가
        redis.call('SADD', KEYS[3], ARGV[2])
        return newPosition  -- 당첨 (포지션 반환)
    else
        -- 제한 초과 시 롤백
        redis.call('DECR', KEYS[1])
        return 0  -- 탈락 (제한 초과)
    end
else
    return 0  -- 탈락 (제한 초과)
end