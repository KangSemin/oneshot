window.swaggerSpec={
  "openapi" : "3.0.1",
  "info" : {
    "title" : "API 문서",
    "description" : "RestDocsWithSwagger Docs",
    "version" : "0.0.1"
  },
  "servers" : [ {
    "url" : "http://localhost:8080"
  }, {
    "url" : "http://43.200.158.39:8080"
  } ],
  "tags" : [ ],
  "paths" : {
    "/api/addresses" : {
      "get" : {
        "tags" : [ "주소 API", "Address API" ],
        "summary" : "주소 목록 조회 API",
        "description" : "사용자의 주소 목록을 페이지네이션 방식으로 조회합니다.\n\n## 사용 예시\n1. 첫 페이지 조회: lastAddressId 파라미터 없이 조회하면 첫 페이지를 반환합니다.\n2. 다음 페이지 조회: 이전 응답의 nextCursor 값을 lastAddressId로 사용하여 다음 페이지를 조회합니다.\n\n## example\n1. 200 OK\n- 주소 목록 조회 성공: 모든 주소 목록 조회 성공\n- 첫 페이지 주소 목록 조회 성공: lastId 파라미터가 없는 첫번째 페이지 조회\n- 빈 주소 목록 조회 성공: 유저가 등록한 주소가 없어 조회된 빈 목록",
        "operationId" : "address-",
        "parameters" : [ {
          "name" : "lastAddressId",
          "in" : "query",
          "description" : "마지막으로 조회한 주소 ID",
          "required" : false,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지 크기",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "10"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-list" : {
                    "value" : "{\"message\":\"주소지 목록 조회가 완료되었습니다.\",\"data\":{\"addresses\":[{\"addressId\":1,\"addressName\":\"집\",\"postAddress\":\"12345\",\"detailAddress\":\"서울시 강서구\",\"default\":false},{\"addressId\":1,\"addressName\":\"집\",\"postAddress\":\"12345\",\"detailAddress\":\"서울시 강서구\",\"default\":false}],\"hasNext\":true,\"nextCursor\":2}}"
                  },
                  "address-controller-test/success-get-addresses-first-page" : {
                    "value" : "{\"message\":\"주소지 목록 조회가 완료되었습니다.\",\"data\":{\"addresses\":[{\"addressId\":1,\"addressName\":\"집\",\"postAddress\":\"12345\",\"detailAddress\":\"서울시 강서구\",\"default\":false}],\"hasNext\":false,\"nextCursor\":1}}"
                  },
                  "address-controller-test/success-get-addresses" : {
                    "value" : "{\"message\":\"주소지 목록 조회가 완료되었습니다.\",\"data\":{\"addresses\":[{\"addressId\":1,\"addressName\":\"집\",\"postAddress\":\"12345\",\"detailAddress\":\"서울시 강서구\",\"default\":false},{\"addressId\":1,\"addressName\":\"집\",\"postAddress\":\"12345\",\"detailAddress\":\"서울시 강서구\",\"default\":false}],\"hasNext\":true,\"nextCursor\":2}}"
                  },
                  "address-controller-test/success-get-addresses-empty-list" : {
                    "value" : "{\"message\":\"주소지 목록 조회가 완료되었습니다.\",\"data\":{\"addresses\":[],\"hasNext\":false,\"nextCursor\":null}}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "Address API" ],
        "summary" : "주소등록 API",
        "description" : "카카오 API에서 주소를 받아와 등록합니다.\n\n## 사용 예시\n1. 기본 주소 등록: 사용자가 처음으로 주소를 등록하는 경우, 자동으로 기본 주소로 설정됩니다.\n2. 추가 주소 등록: 사용자가 여러 주소를 관리할 수 있으며, 기본 주소 여부를 지정할 수 있습니다.\n\n## example\n1. 201 OK \n- 주소 등록 성공 \n2. 400 Bad Request \n- 유효하지 않은 요청 (필수 필드 누락 등)",
        "operationId" : "address-controller-test/success-create-address",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "address-controller-test/success-create-address" : {
                  "value" : "{\"addressName\":\"집\",\"postcode\":\"12345\",\"postAddress\":\"서울시 강서구\",\"detailAddress\":\"아파트 101동 202호\",\"extraAddress\":\"배송전 연락주세요.\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-controller-test/success-create-address" : {
                    "value" : "{\"message\":\"주소 등록이 완료되었습니다.\",\"data\":{\"addressId\":1,\"addressName\":\"집\",\"postcode\":\"12345\",\"postAddress\":\"서울시 강서구\",\"detailAddress\":\"아파트 101동 202호\",\"extraAddress\":\"배송전 연락주세요.\",\"default\":false}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/banners" : {
      "get" : {
        "tags" : [ "Banner API" ],
        "summary" : "배너 목록 조회 API",
        "description" : "배너 목록을 페이지네이션 방식으로 조회합니다.\n\n## 사용 예시\n시작일과 종료일 범위를 지정하여 배너 목록 조회\n\n## example\n1. 200 OK\n- 배너 목록 조회 성공: 시작시간, 종료시간 미입력 - 종료시간 내림차순, startDate, EndDate 파라미터 미입력 시 모든 배너 반환\n- 배너 목록 조회 성공: 시작일 입력 - 입력된 시작일 이후의 배너목록 반환\n- 배너 목록 조회 성공: 종료일 입력 - 입력된 종료일 이전의 배너목록 반환\n- 배너 목록 조회 성공: 시작일 & 종료일 입력 - 입력된 시작일보다 이후이면서 종료일 이전의 배너목록 반환\n- 배너 목록 조회 성공: 빈 목록 조회 - 입력된 정보와 일치파는 배너정보가 없을경우 빈목록 반환",
        "operationId" : "banner-controller-test/success-get-banners",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 번호",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "1"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지 크기",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "10"
          }
        }, {
          "name" : "startDate",
          "in" : "query",
          "description" : "시작일",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "2025-03-10"
          }
        }, {
          "name" : "endDate",
          "in" : "query",
          "description" : "종료일",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "2025-03-11"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "banner-controller-test/success-get-banners" : {
                    "value" : "{\"message\":\"배너 조회가 완료되었습니다.\",\"data\":{\"banners\":[{\"bannerId\":1,\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-11T00:00\"},{\"bannerId\":2,\"eventId\":2,\"imageUrl\":\"https://via.placeholder.com/1200x300/33FF57/000000?text=Limited+Time+Offer\",\"startTime\":\"2025-03-10T18:00\",\"endTime\":\"2025-03-12T18:00\"},{\"bannerId\":3,\"eventId\":3,\"imageUrl\":\"https://via.placeholder.com/1200x300/33A8FF/FFFFFF?text=Special+Promotion\",\"startTime\":\"2025-03-11T18:00\",\"endTime\":\"2025-03-12T18:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "banner-controller-test/success-get-banners-with-start-and-end-date" : {
                    "value" : "{\"message\":\"배너 조회가 완료되었습니다.\",\"data\":{\"banners\":[{\"bannerId\":3,\"eventId\":3,\"imageUrl\":\"https://via.placeholder.com/1200x300/33A8FF/FFFFFF?text=Special+Promotion\",\"startTime\":\"2025-03-11T18:00\",\"endTime\":\"2025-03-12T18:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "banner-controller-test/success-get-banners-with-empty" : {
                    "value" : "{\"message\":\"배너 조회가 완료되었습니다.\",\"data\":{\"banners\":[],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "banner-controller-test/success-get-banners-with-end-date" : {
                    "value" : "{\"message\":\"배너 조회가 완료되었습니다.\",\"data\":{\"banners\":[{\"bannerId\":1,\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-11T00:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "banner-controller-test/success-get-banners-with-start-date" : {
                    "value" : "{\"message\":\"배너 조회가 완료되었습니다.\",\"data\":{\"banners\":[{\"bannerId\":2,\"eventId\":2,\"imageUrl\":\"https://via.placeholder.com/1200x300/33FF57/000000?text=Limited+Time+Offer\",\"startTime\":\"2025-03-10T18:00\",\"endTime\":\"2025-03-12T18:00\"},{\"bannerId\":3,\"eventId\":3,\"imageUrl\":\"https://via.placeholder.com/1200x300/33A8FF/FFFFFF?text=Special+Promotion\",\"startTime\":\"2025-03-11T18:00\",\"endTime\":\"2025-03-12T18:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/carts" : {
      "get" : {
        "tags" : [ "Cart API", "api" ],
        "summary" : "장바구니 조회 성공",
        "description" : "장바구니 조회 성공",
        "operationId" : "cart",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cart/findCart" : {
                    "value" : "{\n  \"message\" : \"장바구니 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"itemList\" : [ {\n      \"cartItemId\" : 1,\n      \"product\" : {\n        \"productId\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"독한 술입니다.\",\n        \"price\" : 120000,\n        \"stockQuantity\" : 50\n      },\n      \"quantity\" : 3\n    } ]\n  }\n}"
                  },
                  "cart-controller-test/success-find-cart" : {
                    "value" : "{\n  \"message\" : \"장바구니 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"itemList\" : [ {\n      \"cartItemId\" : 1,\n      \"product\" : {\n        \"productId\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"독한 술입니다.\",\n        \"price\" : 120000,\n        \"stockQuantity\" : 50\n      },\n      \"quantity\" : 3\n    } ]\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "Cart API", "api" ],
        "summary" : "장바구니 비우기 성공",
        "description" : "장바구니 비우기 성공",
        "operationId" : "cart",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cart/emptyCart" : {
                    "value" : "{\n  \"message\" : \"장바구니 비우기가 완료되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "cart-controller-test/success-empty-cart" : {
                    "value" : "{\n  \"message\" : \"장바구니 비우기가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/chats" : {
      "get" : {
        "tags" : [ "Chat API", "api" ],
        "summary" : "채팅 조회 성공",
        "description" : "채팅 조회 성공",
        "operationId" : "chat",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "chat/findChat" : {
                    "value" : "{\n  \"messageList\" : [ {\n    \"sender\" : \"user\",\n    \"content\" : \"메시지 입니다.\",\n    \"timeMillis\" : 1741314450785\n  } ]\n}"
                  },
                  "chat-controller-test/success-find-chat" : {
                    "value" : "{\n  \"messageList\" : [ {\n    \"sender\" : \"user\",\n    \"content\" : \"메시지 입니다.\",\n    \"timeMillis\" : 1741314450785\n  } ]\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/cocktails" : {
      "post" : {
        "tags" : [ "api", "Cocktail API" ],
        "summary" : "칵테일 생성",
        "description" : "칵테일 생성",
        "operationId" : "cocktail",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "cocktail-controller-test/create-cocktail" : {
                  "value" : "{\n  \"name\" : \"블랙 러시안\",\n  \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n  \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n  \"ingredientList\" : [ {\n    \"ingredientId\" : 1,\n    \"volume\" : \"60ml\"\n  }, {\n    \"ingredientId\" : 4,\n    \"volume\" : \"20ml\"\n  } ]\n}"
                },
                "cocktail/findCocktailsByKeyword" : {
                  "value" : "{\n  \"name\" : \"블랙 러시안\",\n  \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n  \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n  \"ingredientList\" : [ {\n    \"ingredientId\" : 1,\n    \"volume\" : \"60ml\"\n  }, {\n    \"ingredientId\" : 4,\n    \"volume\" : \"20ml\"\n  } ]\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cocktail-controller-test/create-cocktail" : {
                    "value" : "{\n  \"message\" : \"레시피 등록이 완료되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "cocktail/findCocktailsByKeyword" : {
                    "value" : "{\n  \"message\" : \"레시피 등록이 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/coupons" : {
      "get" : {
        "tags" : [ "Coupon API" ],
        "summary" : "쿠폰 목록 조회 API",
        "description" : "쿠폰 목록을 페이지네이션 방식으로 조회합니다.\n\n## 사용 예시\n페이지 번호, 사이즈, 시작일, 종료일 등 조건을 지정하여 쿠폰 목록 조회\n\n## example\n1. 200 OK\n- 쿠폰 목록 조회 성공(쿠폰 만료시간 기준 오름차순): 페이징기반 페이지네이션, startDate, endDate 미입력시 모든 쿠폰 조회\n- 쿠폰 목록 조회 성공: 시작일 입력 - 입력된 시작일 이후의 쿠폰 목록 반환\n- 쿠폰 목록 조회 성공: 종료일 입력 - 입력된 종료일 이전의 쿠폰 목록 반환\n- 쿠폰 목록 조회 성공: 시작일 & 종료일 입력 - 입력된 시작일 이후 그리고 종료일 이전의 쿠폰 목록 반환\n- 쿠폰 목록 조회 성공: 빈 목록 조회 - 입력된 조건과 일치하는 쿠폰이 없을 경우 빈 목록 반환",
        "operationId" : "coupon-controller-test/success-get-",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 번호",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "1"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지 크기",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "10"
          }
        }, {
          "name" : "startDate",
          "in" : "query",
          "description" : "시작일",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "2025-03-10"
          }
        }, {
          "name" : "endDate",
          "in" : "query",
          "description" : "종료일",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "2025-03-11"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-coupons-with-start-and-end-date" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"coupons\":[{\"id\":2,\"couponName\":\"2000원 할인쿠폰\",\"discountValue\":2000}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "coupon-controller-test/success-get-empty-coupons" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"coupons\":[],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "coupon-controller-test/success-get-coupons-with-start-date" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"coupons\":[{\"id\":2,\"couponName\":\"2000원 할인쿠폰\",\"discountValue\":2000},{\"id\":3,\"couponName\":\"3000원 할인쿠폰\",\"discountValue\":3000}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "coupon-controller-test/success-get-coupons-with-end-date" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"coupons\":[{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000},{\"id\":3,\"couponName\":\"3000원 할인쿠폰\",\"discountValue\":3000}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "coupon-controller-test/success-get-coupons" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"coupons\":[{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000},{\"id\":2,\"couponName\":\"2000원 할인쿠폰\",\"discountValue\":2000},{\"id\":3,\"couponName\":\"3000원 할인쿠폰\",\"discountValue\":3000}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/events" : {
      "get" : {
        "tags" : [ "api", "Event API" ],
        "summary" : "이벤트 목록 조회 API",
        "description" : "이벤트 목록을 페이지네이션 방식으로 조회합니다.\n\n## 사용 예시\n상태, 타입, 시작일, 종료일 등 조건을 지정하여 이벤트 목록 조회\n\n## example\n1. 200 OK\n- 이벤트 목록 조회 성공: 종료시간 오름차순 - 페이지네이션 기반 페이징, 상태, 타입, 시작시간, 종료시간이 입력되지 않은 경우 모든 이벤트 목록 반환\n- 이벤트 목록 조회 성공: 상태 조건 입력(ONGOING) - 이벤트 목록 조회 시 ongoing 상태 조건만 입력한 경우\n- 이벤트 목록 조회 성공: 타입 조건 입력(선착순 FCFS) - 이벤트 목록 조회 시 선착순 이벤트 타입만 입력한 경우\n- 이벤트 목록 조회 성공: 시작일 입력 - 이벤트 목록 조회 시 이벤트 시작일만 입력\n- 이벤트 목록 조회 성공: 종료일 입력 - 이벤트 목록 조회 시 종료일만 입력\n- 이벤트 목록 조회 성공: 시작일 & 종료일 입력 - 이벤트 목록 조회 시 시작일과 종료일만 입력, 시작일과 종료일 사이의 목록만 반환\n- 이벤트 목록 조회 성공: 빈 목록 조회 - 이벤트 목록 조회 시 조건과 일치하는 이벤트가 없는 경우 빈 목록 반환",
        "operationId" : "event",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 번호",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "1"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지 크기",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "10"
          }
        }, {
          "name" : "endDate",
          "in" : "query",
          "description" : "종료일",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "2025-03-11"
          }
        }, {
          "name" : "startDate",
          "in" : "query",
          "description" : "시작일",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "2025-03-10"
          }
        }, {
          "name" : "status",
          "in" : "query",
          "description" : "이벤트 상태",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "ongoing"
          }
        }, {
          "name" : "type",
          "in" : "query",
          "description" : "이벤트 타입",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "fcfs"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event-controller-test/success-get-events-with-end-date" : {
                    "value" : "{\n  \"message\" : \"이벤트 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"events\" : [ {\n      \"id\" : 1,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-08T18:00\",\n      \"endTime\" : \"2025-03-09T19:00\"\n    }, {\n      \"id\" : 2,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-07T18:00\",\n      \"endTime\" : \"2025-03-09T18:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "event-controller-test/success-get-events-with-start-and-end-date" : {
                    "value" : "{\n  \"message\" : \"이벤트 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"events\" : [ {\n      \"id\" : 3,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-09T18:00\",\n      \"endTime\" : \"2025-03-10T18:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "event-controller-test/success-get-events-with-status" : {
                    "value" : "{\n  \"message\" : \"이벤트 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"events\" : [ {\n      \"id\" : 3,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-09T18:00\",\n      \"endTime\" : \"2025-03-10T18:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "event-controller-test/success-get-events-with-type" : {
                    "value" : "{\n  \"message\" : \"이벤트 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"events\" : [ {\n      \"id\" : 1,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-08T18:00\",\n      \"endTime\" : \"2025-03-09T19:00\"\n    }, {\n      \"id\" : 3,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-09T18:00\",\n      \"endTime\" : \"2025-03-10T18:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "event-controller-test/success-get-events-with-empty" : {
                    "value" : "{\n  \"message\" : \"이벤트 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"events\" : [ ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "event-controller-test/success-get-events-with-start-date" : {
                    "value" : "{\n  \"message\" : \"이벤트 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"events\" : [ {\n      \"id\" : 1,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-08T18:00\",\n      \"endTime\" : \"2025-03-09T19:00\"\n    }, {\n      \"id\" : 3,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-09T18:00\",\n      \"endTime\" : \"2025-03-10T18:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "event-controller-test/success-get-events" : {
                    "value" : "{\"message\":\"이벤트 목록 조회가 완료되었습니다.\",\"data\":{\"events\":[{\"id\":2,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-07T18:00\",\"endTime\":\"2025-03-09T18:00\"},{\"id\":1,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-08T18:00\",\"endTime\":\"2025-03-09T19:00\"},{\"id\":3,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-10T18:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "event/success-get-events-with-end-date" : {
                    "value" : "{\"message\":\"이벤트 목록 조회가 완료되었습니다.\",\"data\":{\"events\":[{\"id\":1,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-08T18:00\",\"endTime\":\"2025-03-09T19:00\"},{\"id\":2,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-07T18:00\",\"endTime\":\"2025-03-09T18:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "event/success-get-events-with-start-and-end-date" : {
                    "value" : "{\"message\":\"이벤트 목록 조회가 완료되었습니다.\",\"data\":{\"events\":[{\"id\":3,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-10T18:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "event/success-get-events-with-status" : {
                    "value" : "{\"message\":\"이벤트 목록 조회가 완료되었습니다.\",\"data\":{\"events\":[{\"id\":3,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-10T18:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "event/success-get-events-with-type" : {
                    "value" : "{\"message\":\"이벤트 목록 조회가 완료되었습니다.\",\"data\":{\"events\":[{\"id\":1,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-08T18:00\",\"endTime\":\"2025-03-09T19:00\"},{\"id\":3,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-10T18:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "event/success-get-events-with-empty" : {
                    "value" : "{\"message\":\"이벤트 목록 조회가 완료되었습니다.\",\"data\":{\"events\":[],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "event/success-get-events-with-start-date" : {
                    "value" : "{\"message\":\"이벤트 목록 조회가 완료되었습니다.\",\"data\":{\"events\":[{\"id\":1,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-08T18:00\",\"endTime\":\"2025-03-09T19:00\"},{\"id\":3,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-10T18:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/favorites" : {
      "get" : {
        "tags" : [ "api", "Favorite API" ],
        "summary" : "즐겨찾기 목록 조회 API",
        "description" : "즐겨찾기 목록 조회 API",
        "operationId" : "favorite",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 번호",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "1"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지 크기",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "10"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-get-favorites" : {
                    "value" : "{\n  \"message\" : \"즐겨찾기 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"favorites\" : [ {\n      \"cocktailId\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"type\" : \"OFFICIAL\",\n      \"favoritedAt\" : \"2025-03-10T01:01:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "favorite-controller-test/success-get-empty-favorites" : {
                    "value" : "{\n  \"message\" : \"즐겨찾기 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"favorites\" : [ ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "favorite/success-get-favorites" : {
                    "value" : "{\"message\":\"즐겨찾기 목록 조회가 완료되었습니다.\",\"data\":{\"favorites\":[{\"cocktailId\":1,\"name\":\"블랙 러시안\",\"description\":\"보드카와 깔루아로 만드는 칵테일\",\"type\":\"OFFICIAL\",\"favoritedAt\":\"2025-03-10T01:01:00\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "favorite/success-get-empty-favorites" : {
                    "value" : "{\"message\":\"즐겨찾기 목록 조회가 완료되었습니다.\",\"data\":{\"favorites\":[],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/ingredients" : {
      "post" : {
        "tags" : [ "api", "Ingredient_API" ],
        "summary" : "재료 생성 실패 case2",
        "description" : " 재료를 생성하는 API, 이미지 업로드에 실패로 재료 생성 실패.",
        "operationId" : "ingredient",
        "requestBody" : {
          "content" : {
            "multipart/form-data;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "ingredient-controller-test/재료생성_성공" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"보드카\",\n  \"category\" : \"VODKA\",\n  \"avb\" : 40.0\n}"
                },
                "ingredient/create" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"보드카\",\n  \"category\" : \"VODKA\",\n  \"avb\" : 40.0\n}"
                }
              }
            },
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "ingredient-controller-test/재료생성_실패_일반유저_시도" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"보드카\",\n  \"category\" : \"VODKA\",\n  \"avb\" : 40.0\n}"
                },
                "ingredient-controller-test/재료생성_실패_이미지업로드_실패" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"보드카\",\n  \"category\" : \"VODKA\",\n  \"avb\" : 40.0\n}"
                },
                "ingredient/create-failedCase2" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"보드카\",\n  \"category\" : \"VODKA\",\n  \"avb\" : 40.0\n}"
                },
                "ingredient/create-failedCase1" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"보드카\",\n  \"category\" : \"VODKA\",\n  \"avb\" : 40.0\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료생성_성공" : {
                    "value" : "{\n  \"message\" : \"재료 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"보드카\",\n    \"category\" : \"VODKA\",\n    \"imageUrl\" : \"대충 이미지 주소\",\n    \"avb\" : 40.0\n  }\n}"
                  },
                  "ingredient/create" : {
                    "value" : "{\n  \"message\" : \"재료 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"보드카\",\n    \"category\" : \"VODKA\",\n    \"imageUrl\" : \"대충 이미지 주소\",\n    \"avb\" : 40.0\n  }\n}"
                  }
                }
              }
            }
          },
          "403" : {
            "description" : "403",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료생성_실패_일반유저_시도" : {
                    "value" : "{\n  \"httpStatus\" : \"FORBIDDEN\",\n  \"errorMessage\" : \"접근 권한이 없습니다.\"\n}"
                  },
                  "ingredient/create-failedCase1" : {
                    "value" : "{\n  \"httpStatus\" : \"FORBIDDEN\",\n  \"errorMessage\" : \"접근 권한이 없습니다.\"\n}"
                  }
                }
              }
            }
          },
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료생성_실패_이미지업로드_실패" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"이미지 파일만 업로드 가능합니다\"\n}"
                  },
                  "ingredient/create-failedCase2" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"이미지 파일만 업로드 가능합니다\"\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/orders" : {
      "get" : {
        "tags" : [ "Order API", "api" ],
        "summary" : "주문 전체 조회 성공",
        "description" : "주문 전체 조회 성공",
        "operationId" : "order",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 넘버",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지당 항목 수",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "order/getAllOrder" : {
                    "value" : "{\n  \"message\" : \"주문 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"orderId\" : 1,\n      \"orderNumber\" : \"23031114553289\",\n      \"name\" : \"보드카 외 2개\",\n      \"amount\" : 120000,\n      \"status\" : \"PENDING_PAYMENT\",\n      \"orderDate\" : \"2025-03-10T16:08:17.783333\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  },
                  "order-controller-test/success-get-all-order" : {
                    "value" : "{\n  \"message\" : \"주문 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"orderId\" : 1,\n      \"orderNumber\" : \"23031114553289\",\n      \"name\" : \"보드카 외 2개\",\n      \"amount\" : 120000,\n      \"status\" : \"PENDING_PAYMENT\",\n      \"orderDate\" : \"2025-03-10T16:08:17.783333\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "Order API", "api" ],
        "summary" : "주문 생성 성공",
        "description" : "주문 생성 성공",
        "operationId" : "order",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "order/createOrder" : {
                  "value" : "{\n  \"addressId\" : 1\n}"
                },
                "order-controller-test/success-create-order" : {
                  "value" : "{\n  \"addressId\" : 1\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "order/createOrder" : {
                    "value" : "{\n  \"message\" : \"주문이 완료되었습니다.\",\n  \"data\" : {\n    \"orderId\" : 1,\n    \"orderNumber\" : \"23031114553289\",\n    \"name\" : \"보드카 외 2개\",\n    \"amount\" : 120000,\n    \"status\" : \"PENDING_PAYMENT\",\n    \"orderDate\" : \"2025-03-10T16:08:17.783333\"\n  }\n}"
                  },
                  "order-controller-test/success-create-order" : {
                    "value" : "{\n  \"message\" : \"주문이 완료되었습니다.\",\n  \"data\" : {\n    \"orderId\" : 1,\n    \"orderNumber\" : \"23031114553289\",\n    \"name\" : \"보드카 외 2개\",\n    \"amount\" : 120000,\n    \"status\" : \"PENDING_PAYMENT\",\n    \"orderDate\" : \"2025-03-10T16:08:17.783333\"\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/pantries" : {
      "get" : {
        "tags" : [ "Pantry_API", "api" ],
        "summary" : "나의 펜트리 조회 ",
        "description" : "로그인한 사용자의 펜트리 목록을 조회합니다",
        "operationId" : "pantry",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "pantry/getMyPantry" : {
                    "value" : "{\n  \"message\" : \"팬트리 조회가 완료되었습니다.\",\n  \"data\" : [ {\n    \"userId\" : 1,\n    \"pantryId\" : 1,\n    \"ingredient\" : {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }\n  } ]\n}"
                  },
                  "pantry-controller-test/펜트리_조회_성공" : {
                    "value" : "{\n  \"message\" : \"팬트리 조회가 완료되었습니다.\",\n  \"data\" : [ {\n    \"userId\" : 1,\n    \"pantryId\" : 1,\n    \"ingredient\" : {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }\n  } ]\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "Pantry_API", "api" ],
        "summary" : "펜트리 비움",
        "description" : "사용자의 펜트리에 있는 모든 재료를 삭제합니다",
        "operationId" : "pantry",
        "responses" : {
          "204" : {
            "description" : "204",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "pantry/clearPantryIngredients" : {
                    "value" : "{\n  \"message\" : \"팬트리 비우기가 완료되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "pantry-controller-test/펜트리비우기_성공" : {
                    "value" : "{\n  \"message\" : \"팬트리 비우기가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/products" : {
      "get" : {
        "tags" : [ "api", "Product API" ],
        "summary" : "상품 전체 조회 성공",
        "description" : "상품 전체 조회 성공",
        "operationId" : "product",
        "parameters" : [ {
          "name" : "category",
          "in" : "query",
          "description" : "상품의 카테고리",
          "required" : false,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 넘버",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지당 항목 수",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "product-controller-test/success-get-all-product" : {
                    "value" : "{\n  \"message\" : \"상품 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"productId\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"독한 술입니다.\",\n      \"price\" : 120000,\n      \"stockQuantity\" : 50\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  },
                  "product/getAllProduct" : {
                    "value" : "{\n  \"message\" : \"상품 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"productId\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"독한 술입니다.\",\n      \"price\" : 120000,\n      \"stockQuantity\" : 50\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "api", "Product API" ],
        "summary" : "상품 생성 성공",
        "description" : "상품 생성 성공",
        "operationId" : "product",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "product-controller-test/success-create-product" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"독한 술입니다.\",\n  \"category\" : \"ALCOHOL\",\n  \"price\" : 120000,\n  \"stockQuantity\" : 50,\n  \"status\" : \"SALE\"\n}"
                },
                "product/createProduct" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"독한 술입니다.\",\n  \"category\" : \"ALCOHOL\",\n  \"price\" : 120000,\n  \"stockQuantity\" : 50,\n  \"status\" : \"SALE\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "product-controller-test/success-create-product" : {
                    "value" : "{\n  \"message\" : \"상품 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"productId\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"독한 술입니다.\",\n    \"price\" : 120000,\n    \"stockQuantity\" : 50\n  }\n}"
                  },
                  "product/createProduct" : {
                    "value" : "{\n  \"message\" : \"상품 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"productId\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"독한 술입니다.\",\n    \"price\" : 120000,\n    \"stockQuantity\" : 50\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/users" : {
      "get" : {
        "tags" : [ "User API", "api" ],
        "summary" : "유저 정보 조회 API",
        "description" : "로그인한 사용자의 정보를 조회합니다.\n\n## 사용 예시\n로그인 상태에서 본인 정보 조회\n\n## example\n1. 200 OK\n- 유저 정보 조회 성공: 로그인한 사용자가 본인 정보 조회\n2. 404 Not Found\n- 유저 정보 조회 실패: 존재하지 않거나 소프르딜리트 된 유저아이디 - 존재하지 않거나 소프트딜리트된 유저아이디로 정보조회 시 예외 발생(탈퇴시 토큰무효화를 하지만, 혹시모를 경우로 구현)",
        "operationId" : "user",
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "user/invalid-user-id-get-user-info" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"유저가 존재하지 않습니다.\"}"
                  },
                  "user-controller-test/invalid-user-id-get-user-info" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"유저가 존재하지 않습니다.\"\n}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "user-controller-test/success-get-user-info" : {
                    "value" : "{\"message\":\"사용자 조회가 완료되었습니다.\",\"data\":{\"id\":1,\"email\":\"test@mail.com\",\"nickname\":\"nickname\",\"userRole\":\"USER\"}}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "User API", "api" ],
        "summary" : "유저 소프트 딜리트 API",
        "description" : "로그인한 사용자의 계정을 비활성화(소프트 딜리트)합니다.\n\n## 사용 예시\n로그인 상태에서 회원 탈퇴 요청\n\n## example\n1. 200 OK\n- 유저 소프트 딜리트 성공: 유저 소프트 딜리트 시 해당 어세스토큰을 남은 시간만큼의 TTL로 블랙리스트에 등록, 리프레시토큰 삭제\n2. 409 Conflict\n- 유저 소프트 딜리트 실패: 존재하지 않거나 소프르딜리트 된 유저아이디 - 존재하지 않거나 소프트딜리트된 유저아이디로 삭제시도 시 예외 발생(탈퇴시 토큰무효화를 하지만, 혹시모를 경우로 구현)",
        "operationId" : "user",
        "responses" : {
          "409" : {
            "description" : "409",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "user/invalid-user-id-delete-user" : {
                    "value" : "{\"httpStatus\":\"CONFLICT\",\"errorMessage\":\"이미 탈퇴한 사용자입니다.\"}"
                  },
                  "user-controller-test/invalid-user-id-delete-user" : {
                    "value" : "{\n  \"httpStatus\" : \"CONFLICT\",\n  \"errorMessage\" : \"이미 탈퇴한 사용자입니다.\"\n}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "user/success-delete-user" : {
                    "value" : "{\"message\":\"사용자 삭제가 완료되었습니다.\",\"data\":1}"
                  },
                  "user-controller-test/success-delete-user" : {
                    "value" : "{\n  \"message\" : \"사용자 삭제가 완료되었습니다.\",\n  \"data\" : 1\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "User API", "api" ],
        "summary" : "유저 정보 수정 API",
        "description" : "로그인한 사용자의 정보를 수정합니다.\n\n## 사용 예시\n닉네임, 비밀번호 등 정보 수정\n\n## example\n1. 200 OK\n- 유저 정보 수정 성공: 로그인한 사용자 본인의 닉네임, 패스워드 변경\n2. 404 Not Found\n- 유저 정보 수정 실패: 존재하지 않거나 소프르딜리트 된 유저아이디 - 존재하지 않거나 소프트딜리트된 유저아이디로 수정시도 시 예외 발생(탈퇴시 토큰무효화를 하지만, 혹시모를 경우로 구현)",
        "operationId" : "user",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "user/success-update-user-info" : {
                  "value" : "{\"nickname\":\"nickname\",\"password\":\"rawPassword123!\"}"
                },
                "user/invalid-user-id-update-user-info" : {
                  "value" : "{\"nickname\":\"nickname\",\"password\":\"rawPassword123!\"}"
                },
                "user-controller-test/success-update-user-info" : {
                  "value" : "{\n  \"nickname\" : \"nickname\",\n  \"password\" : \"rawPassword123!\"\n}"
                },
                "user-controller-test/invalid-user-id-update-user-info" : {
                  "value" : "{\n  \"nickname\" : \"nickname\",\n  \"password\" : \"rawPassword123!\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "user/success-update-user-info" : {
                    "value" : "{\"message\":\"사용자 수정이 완료되었습니다.\",\"data\":{\"id\":1,\"email\":\"test@mail.com\",\"nickname\":\"nickname\",\"userRole\":\"USER\"}}"
                  },
                  "user-controller-test/success-update-user-info" : {
                    "value" : "{\n  \"message\" : \"사용자 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"email\" : \"test@mail.com\",\n    \"nickname\" : \"nickname\",\n    \"userRole\" : \"USER\"\n  }\n}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "user/invalid-user-id-update-user-info" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"유저가 존재하지 않습니다.\"}"
                  },
                  "user-controller-test/invalid-user-id-update-user-info" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"유저가 존재하지 않습니다.\"\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/addresses/{addressId}" : {
      "get" : {
        "tags" : [ "Address API" ],
        "summary" : "주소 단건 조회 API",
        "description" : "주소 ID를 기반으로 특정 주소의 상세 정보를 조회합니다.\n\n## 사용 예시\n주소 id로 단건 조회, 사용자 본인의 주소만 조회 가능\n\n## example\n1. 200 OK\n- 주소 단건 조회 성공\n2. 404 Not Found\n- 주소 조회 실패: 존재하지 않는 주소로 조회 시도",
        "operationId" : "address-controller-test/",
        "parameters" : [ {
          "name" : "addressId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-address-id-get-address" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"해당 주소가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-controller-test/success-get-address" : {
                    "value" : "{\"message\":\"주소 조회가 완료되었습니다.\",\"data\":{\"addressId\":1,\"addressName\":\"집\",\"postcode\":\"12345\",\"postAddress\":\"서울시 강서구\",\"detailAddress\":\"아파트 101동 202호\",\"extraAddress\":\"배송전 연락주세요.\",\"default\":false}}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "api", "Address API" ],
        "summary" : "주소 삭제 API",
        "description" : "주소 ID를 기반으로 특정 주소를 삭제합니다.\n\n## 사용 예시\n주소 id로 주소 삭제, 사용자 본인의 주소만 삭제\n\n## example\n1. 200 OK\n- 주소 삭제 성공\n2. 400 Bad Request\n- 주소 삭제 실패: 디폴트주소 삭제 시도 - 기본주소인 주소를 삭제시도 시 예외발생\n3. 404 Not Found\n- 주소 삭제 실패: 존재하지 않는 주소로 조회 시도",
        "operationId" : "address-controller-test/",
        "parameters" : [ {
          "name" : "addressId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-controller-test/success-address-delete" : {
                    "value" : "{\n  \"message\" : \"주소 삭제가 완료되었습니다.\",\n  \"data\" : 1\n}"
                  },
                  "address-controller-test/success-delete-address" : {
                    "value" : "{\"message\":\"주소 삭제가 완료되었습니다.\",\"data\":1}"
                  }
                }
              }
            }
          },
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-default-delete-address" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"기본 주소는 삭제할 수 없습니다.\"}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-address-id-delete-address" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"해당 주소가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "Address API" ],
        "summary" : "주소 수정 API",
        "description" : "기존 주소 정보를 수정합니다.\n\n## 사용 예시\n1. 주소 정보 업데이트: 상세 주소나 별칭 등의 정보를 수정합니다.\n2. 기본 주소 설정: 기존 주소를 기본 주소로 변경할 수 있습니다.\n\n## example\n1. 200 OK\n- 주소 수정 성공: 카카오 API에서 받아온 정보로 사용자 본인의 주소 수정\n2. 400 Bad Request\n- 주소 수정 실패: 디폴트인 주소 수정 - 기본주소 상태인 주소의 기본주소 여부를 False로 변경 시도\n3. 404 Not Found\n- 주소 수정 실패: 존재하지 않는 주소로 수정 시도",
        "operationId" : "address-controller-test/",
        "parameters" : [ {
          "name" : "addressId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "address-controller-test/invalid-default-update-address" : {
                  "value" : "{\"addressName\":\"새집\",\"postcode\":\"23456\",\"postAddress\":\"서울시 강남구\",\"detailAddress\":\"아파트 202동 303호\",\"extraAddress\":\"배송전 연락주세요.\",\"default\":false}"
                },
                "address-controller-test/invalid-address-id-update-address" : {
                  "value" : "{\"addressName\":\"새집\",\"postcode\":\"23456\",\"postAddress\":\"서울시 강남구\",\"detailAddress\":\"아파트 202동 303호\",\"extraAddress\":\"배송전 연락주세요.\",\"default\":false}"
                },
                "address-controller-test/success-update-address" : {
                  "value" : "{\"addressName\":\"새집\",\"postcode\":\"23456\",\"postAddress\":\"서울시 강남구\",\"detailAddress\":\"아파트 202동 303호\",\"extraAddress\":\"배송전 연락주세요.\",\"default\":false}"
                }
              }
            }
          }
        },
        "responses" : {
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-default-update-address" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"기본 주소는 필수입니다\"}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-address-id-update-address" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"해당 주소가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "address-controller-test/success-update-address" : {
                    "value" : "{\"message\":\"주소 수정이 완료되었습니다.\",\"data\":{\"addressId\":1,\"addressName\":\"새집\",\"postcode\":\"23456\",\"postAddress\":\"서울시 강남구\",\"detailAddress\":\"아파트 202동 303호\",\"extraAddress\":\"배송전 연락주세요.\",\"default\":false}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/banners" : {
      "post" : {
        "tags" : [ "api", "Banner API" ],
        "summary" : "ADMIN 배너 생성 API",
        "description" : "관리자가 새로운 배너를 생성합니다.\n\n## 사용 예시\n이벤트 ID, 이미지 URL, 시작 시간, 종료 시간 정보를 입력하여 배너 생성\n\n## example\n1. 201 Created\n- ADMIN 배너 생성 성공: 관리자가 배너 생성, 입력한 시작일과 종료일에 맞춰 상태변경 스케쥴러 실행\n2. 404 Not Found\n- ADMIN 배너 생성 실패: 존재하지 않는 이벤트 아이디로 배너 생성 - 관리자가 존재하지 않는 이벤트 아이디로 배너생성 시도 시 예외발생",
        "operationId" : "admin-banner-controller-test/",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "admin-banner-controller-test/invalid-date-update-banner" : {
                  "value" : "{\n  \"eventId\" : 1,\n  \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n  \"startDate\" : \"2025-03-08\",\n  \"startTime\" : \"18:00\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"2025-03-09\"\n}"
                },
                "admin-banner-controller-test/invalid-date-create-banner" : {
                  "value" : "{\n  \"eventId\" : 1,\n  \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n  \"startDate\" : \"2025-03-08\",\n  \"startTime\" : \"18:00\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"2025-03-09\"\n}"
                },
                "admin-banner-controller-test/invalid-banner-id-create-banner" : {
                  "value" : "{\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\"}"
                },
                "admin-banner-controller-test/success-create-banner" : {
                  "value" : "{\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/invalid-date-update-banner" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"시간 형식은 HH:MM 형태여야 합니다\"\n}"
                  },
                  "admin-banner-controller-test/invalid-date-create-banner" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"시간 형식은 HH:MM 형태여야 합니다\"\n}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/invalid-banner-id-create-banner" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"이벤트가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/success-create-banner" : {
                    "value" : "{\"message\":\"배너 등록이 완료되었습니다.\",\"data\":{\"bannerId\":1,\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-11T00:00\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/chats" : {
      "get" : {
        "tags" : [ "Chat API", "api" ],
        "summary" : "어드민용 채팅방 리스트 조회 성공",
        "description" : "어드민용 채팅방 리스트 조회 성공",
        "operationId" : "chat",
        "parameters" : [ {
          "name" : "cursor",
          "in" : "query",
          "description" : "불러온 마지막 항목",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        }, {
          "name" : "limit",
          "in" : "query",
          "description" : "로딩되는 항목 갯수",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "chat/findChatListForAdmin" : {
                    "value" : "{\n  \"chatList\" : [ {\n    \"userId\" : 1,\n    \"lastMessage\" : \"u::메시지 입니다.::1741314450785\"\n  } ],\n  \"nextCursor\" : \"\"\n}"
                  },
                  "chat-controller-test/success-find-chat-list-for-admin" : {
                    "value" : "{\n  \"chatList\" : [ {\n    \"userId\" : 1,\n    \"lastMessage\" : \"u::메시지 입니다.::1741314450785\"\n  } ],\n  \"nextCursor\" : \"\"\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/coupons" : {
      "post" : {
        "tags" : [ "Coupon API" ],
        "summary" : "ADMIN 쿠폰 생성 API",
        "description" : "관리자가 새로운 쿠폰을 생성합니다.\n\n## 사용 예시\n쿠폰명, 할인 값, 시작 시간, 종료 시간 등 정보를 입력하여 쿠폰 생성\n\n## example\n1. 201 Created\n- ADMIN 쿠폰 생성 성공: 관리자가 쿠폰 생성",
        "operationId" : "admin-coupon-controller-test/success-create-coupon",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "admin-coupon-controller-test/success-create-coupon" : {
                  "value" : "{\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000,\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-create-coupon" : {
                    "value" : "{\"message\":\"쿠폰 등록이 완료되었습니다.\",\"data\":{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/deliveries" : {
      "post" : {
        "tags" : [ "api", "Delivery API" ],
        "summary" : "ADMIN 배송정보 등록 API",
        "description" : "관리자가 배송 정보를 등록합니다.\n\n## 사용 예시\n주문 ID, 수령인 정보, 배송 메시지, 택배사, 운송장 번호 등 정보 입력\n\n## example\n1. 201 Created\n- ADMIN 배송정보 등록 성공: 관리자가 운송장 번호로 배송정보 등록\n2. 409 Conflict\n- ADMIN 배송정보 등록 실패: 이미 배송정보가 존재하는 주문정보로 등록 - 관리자가 이미 배송정보 보다 등록된 주문에 배송정보 등록시도 시 예외발생\n3. 404 Not Found\n- ADMIN 배송정보 등록 실패: 존재하지 않는 주문아이디로 등록 - 관리자가 존재하지 않는 주문아이디로 배송정보 등록 시도 시 예외발생\n4. 400 Bad Request\n- ADMIN 배송정보 등록 실패: 유효하지 않은 주문상태로 등록 - 관리자가 주문상태가 'PROCESSING'이 아닌 경우 예외 발생",
        "operationId" : "admin-delivery-controller-test/conflict-order-id-create-deliveryadmin-delivery-controller-test/invalid-order-id-create-deliveryadmin-delivery-controller-test/invalid-order-state-create-deliveryadmin-delivery-controller-test/success-create-deliverydelivery/conflict-order-id-create-deliverydelivery/invalid-order-id-create-deliverydelivery/invalid-order-state-create-delivery",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "admin-delivery-controller-test/invalid-order-id-create-delivery" : {
                  "value" : "{\n  \"orderId\" : 1,\n  \"receiverName\" : \"봉미선\",\n  \"receiverPhone\" : \"010-1234-4567\",\n  \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n  \"trackingNumber\" : \"123456789\",\n  \"courierCompany\" : \"cjgls\"\n}"
                },
                "admin-delivery-controller-test/invalid-order-state-create-delivery" : {
                  "value" : "{\n  \"orderId\" : 1,\n  \"receiverName\" : \"봉미선\",\n  \"receiverPhone\" : \"010-1234-4567\",\n  \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n  \"trackingNumber\" : \"123456789\",\n  \"courierCompany\" : \"cjgls\"\n}"
                },
                "admin-delivery-controller-test/success-create-delivery" : {
                  "value" : "{\"orderId\":1,\"receiverName\":\"봉미선\",\"receiverPhone\":\"010-1234-4567\",\"deliveryMessage\":\"현관 비밀번호는 #0000#입니다.\",\"trackingNumber\":\"123456789\",\"courierCompany\":\"cjgls\"}"
                },
                "admin-delivery-controller-test/conflict-order-id-create-delivery" : {
                  "value" : "{\n  \"orderId\" : 1,\n  \"receiverName\" : \"봉미선\",\n  \"receiverPhone\" : \"010-1234-4567\",\n  \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n  \"trackingNumber\" : \"123456789\",\n  \"courierCompany\" : \"cjgls\"\n}"
                },
                "delivery/invalid-order-id-create-delivery" : {
                  "value" : "{\"orderId\":1,\"receiverName\":\"봉미선\",\"receiverPhone\":\"010-1234-4567\",\"deliveryMessage\":\"현관 비밀번호는 #0000#입니다.\",\"trackingNumber\":\"123456789\",\"courierCompany\":\"cjgls\"}"
                },
                "delivery/invalid-order-state-create-delivery" : {
                  "value" : "{\"orderId\":1,\"receiverName\":\"봉미선\",\"receiverPhone\":\"010-1234-4567\",\"deliveryMessage\":\"현관 비밀번호는 #0000#입니다.\",\"trackingNumber\":\"123456789\",\"courierCompany\":\"cjgls\"}"
                },
                "delivery/conflict-order-id-create-delivery" : {
                  "value" : "{\"orderId\":1,\"receiverName\":\"봉미선\",\"receiverPhone\":\"010-1234-4567\",\"deliveryMessage\":\"현관 비밀번호는 #0000#입니다.\",\"trackingNumber\":\"123456789\",\"courierCompany\":\"cjgls\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-order-id-create-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"주문이 존재하지 않습니다.\"\n}"
                  },
                  "delivery/invalid-order-id-create-delivery" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"주문이 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-order-state-create-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"배송 대기 중인 주문만 등록할 수 있습니다\"\n}"
                  },
                  "delivery/invalid-order-state-create-delivery" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"배송 대기 중인 주문만 등록할 수 있습니다\"}"
                  }
                }
              }
            }
          },
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/success-create-delivery" : {
                    "value" : "{\"message\":\"배송 정보 등록이 완료되었습니다.\",\"data\":{\"deliveryId\":2,\"orderId\":1,\"receiverName\":\"봉미선\",\"receiverPhone\":\"010-1234-4567\",\"deliveryMessage\":\"현관 비밀번호는 #0000#입니다.\",\"courierCompany\":\"CJGLS\",\"trackingNumber\":\"123456789\",\"status\":\"REGISTERED\",\"createdAt\":\"2025-03-11T00:00:00\"}}"
                  }
                }
              }
            }
          },
          "409" : {
            "description" : "409",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/conflict-order-id-create-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"CONFLICT\",\n  \"errorMessage\" : \"이미 배송정보가 존재하는 주문입니다.\"\n}"
                  },
                  "delivery/conflict-order-id-create-delivery" : {
                    "value" : "{\"httpStatus\":\"CONFLICT\",\"errorMessage\":\"이미 배송정보가 존재하는 주문입니다.\"}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/events" : {
      "post" : {
        "tags" : [ "Event API", "api" ],
        "summary" : "ADMIN 이벤트 생성 API",
        "description" : "관리자가 새로운 이벤트를 생성합니다.\n\n## 사용 예시\n이벤트명, 설명, 시작 시간, 종료 시간, 이벤트 상세 정보 등 입력\n\n## example\n1. 201 Created\n- ADMIN 이벤트 생성 성공: 관리자가 이벤트 생성\n2. 400 Bad Request\n- ADMIN 이벤트 생성 실패: 이벤트 종료 시간이 현재 시간보다 이전일 때 - 관리자가 현재 시간 이전인 이벤트 종료시간 입력 시 예외 발생\n- ADMIN 이벤트 생성 실패: 이벤트 시작 시간이 종료 시간보다 이후일 때 - 관리자가 이벤트 종료시간 이후의 시작시간을 입력 시 예외 발생\n- ADMIN 이벤트 생성 실패: 상세 정보 누락 - 관리자가 이벤트 생성 시 입력한 Object 필드에 필요한 정보가 입력되지 않았을 때 예외 발생\n- ADMIN 이벤트 생성 실패: 이벤트 디테일 Object를 String으로 파싱 실패 - 관리자가 입력한 이벤트 디테일 Object를 String으로 파싱 실패 시 예외발생",
        "operationId" : "admin-event-controller-test/fail-parsing-create-eventadmin-event-controller-test/invalid-end-time-create-eventadmin-event-controller-test/invalid-start-time-create-eventadmin-event-controller-test/missing-details-create-eventadmin-event-controller-test/success-create-eventevent/fail-parsing-create-eventevent/invalid-end-time-create-eventevent/invalid-start-time-create-eventevent/missing-details-create-event",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "event/invalid-start-time-create-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-05-07\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-02\",\"endTime\":\"16:58\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "event/missing-details-create-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "event/invalid-end-time-create-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-03-09\",\"endTime\":\"19:00\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "event/fail-parsing-create-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "admin-event-controller-test/invalid-start-time-create-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-05-07\",\n  \"startTime\" : \"16:58\",\n  \"endDate\" : \"2025-05-02\",\n  \"endTime\" : \"16:58\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponName\" : \"20% 할인 쿠폰\",\n    \"couponId\" : 1\n  }\n}"
                },
                "admin-event-controller-test/success-create-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "admin-event-controller-test/missing-details-create-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-04-28\",\n  \"startTime\" : \"16:58\",\n  \"endDate\" : \"2025-05-04\",\n  \"endTime\" : \"16:58\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponName\" : \"20% 할인 쿠폰\",\n    \"couponId\" : 1\n  }\n}"
                },
                "admin-event-controller-test/invalid-end-time-create-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-04-28\",\n  \"startTime\" : \"16:58\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"19:00\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponName\" : \"20% 할인 쿠폰\",\n    \"couponId\" : 1\n  }\n}"
                },
                "admin-event-controller-test/fail-parsing-create-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-04-28\",\n  \"startTime\" : \"16:58\",\n  \"endDate\" : \"2025-05-04\",\n  \"endTime\" : \"16:58\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponName\" : \"20% 할인 쿠폰\",\n    \"couponId\" : 1\n  }\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event/invalid-start-time-create-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"종료일이 시작일보다 이전입니다.\"}"
                  },
                  "event/missing-details-create-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"쿠폰 정보가 누락되었습니다.\"}"
                  },
                  "event/invalid-end-time-create-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"종료일이 현재 시간보다 이전입니다.\"}"
                  },
                  "event/fail-parsing-create-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"JSON 데이터 검증 중 오류가 발생했습니다.\"}"
                  },
                  "admin-event-controller-test/invalid-start-time-create-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"종료일이 시작일보다 이전입니다.\"\n}"
                  },
                  "admin-event-controller-test/missing-details-create-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"쿠폰 정보가 누락되었습니다.\"\n}"
                  },
                  "admin-event-controller-test/invalid-end-time-create-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"종료일이 현재 시간보다 이전입니다.\"\n}"
                  },
                  "admin-event-controller-test/fail-parsing-create-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"JSON 데이터 검증 중 오류가 발생했습니다.\"\n}"
                  }
                }
              }
            }
          },
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-event-controller-test/success-create-event" : {
                    "value" : "{\"message\":\"이벤트 등록이 완료되었습니다.\",\"data\":{\"id\":1,\"name\":\"상품 증정 선착순 이벤트\",\"startTime\":\"2025-03-08T18:00\",\"endTime\":\"2025-03-09T19:00\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/auth/login" : {
      "post" : {
        "tags" : [ "Auth API" ],
        "summary" : "로그인 API",
        "description" : "이메일과 비밀번호를 입력받아 로그인을 처리합니다.\n\n## 사용 예시\n이메일과 비밀번호를 입력하여 로그인 진행\n\n## example\n1. 200 OK\n- 로그인 성공: 로그인 시, 어세스토큰 반환 & 어세스토큰과 리프레시토큰 쿠키에 저장\n2. 404 Not Found\n- 로그인 실패: 존재하지 않는 메일로 로그인 - 존재하지 않는 메일로 로그인 시 예외발생\n3. 401 Unauthorized\n- 로그인 실패: 비밀번호 불일치 - 비밀번호 불일치시 예외발생",
        "operationId" : "auth-controller-test/",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "auth-controller-test/invalid-password-log-in" : {
                  "value" : "{\"email\":\"test@mail.com\",\"password\":\"rawPassword123!\"}"
                },
                "auth-controller-test/invalid-email-log-in" : {
                  "value" : "{\"email\":\"test@mail.com\",\"password\":\"rawPassword123!\"}"
                },
                "auth-controller-test/success-log-in" : {
                  "value" : "{\"email\":\"test@mail.com\",\"password\":\"rawPassword123!\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "401" : {
            "description" : "401",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "auth-controller-test/invalid-password-log-in" : {
                    "value" : "{\"httpStatus\":\"UNAUTHORIZED\",\"errorMessage\":\"잘못된 아이디 또는 비밀번호입니다.\"}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "auth-controller-test/invalid-email-log-in" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"유저가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "auth-controller-test/success-log-in" : {
                    "value" : "{\"message\":\"로그인이 완료되었습니다.\",\"data\":{\"accessToken\":\"accessToken\",\"expiresAt\":1741341143}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/auth/logout" : {
      "post" : {
        "tags" : [ "Auth API" ],
        "summary" : "로그아웃 API",
        "description" : "로그인된 사용자의 로그아웃을 처리합니다.\n\n## 사용 예시\n로그인 상태에서 로그아웃 요청\n\n## example\n1. 200 OK\n- 로그아웃 성공: 로그아웃 시, 해당 어세스토큰을 남은 유효시간만큼의 TTL 값으로 레디스 블랙리스트에 등록",
        "operationId" : "auth-controller-test/success-log-out",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "auth-controller-test/success-log-out" : {
                    "value" : "{\"message\":\"로그아웃이 완료되었습니다.\",\"data\":1}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/auth/refresh" : {
      "post" : {
        "tags" : [ "Auth API" ],
        "summary" : "토큰 갱신 API",
        "description" : "리프레시 토큰을 사용하여 액세스 토큰을 재발급합니다.\n\n## 사용 예시\n리프레시 토큰을 쿠키에 담아 요청\n\n## example\n1. 200 OK\n- 리프레시 토큰으로 엑세스 토큰 재발급 성공: 쿠키에 저장된 리프레시토큰으로 어세스토큰 재발급 성공, 사용된 리프레시토큰은 무효화 후 새로운 리프레시토큰 쿠키에 저장\n2. 403 Forbidden\n- 리프레시 토큰으로 엑세스 토큰 재발급 실패: 유저아이디와 리프레시토큰 불일치 - 리프레시 토큰에 저장된 유저아이디와 DB에 저장된 리프레시토큰의 정보가 다를 경우 예외발생",
        "operationId" : "auth-controller-test/",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "auth-controller-test/success-refresh-token" : {
                    "value" : "{\"message\":\"토큰 발급이 완료되었습니다.\",\"data\":{\"accessToken\":\"accessToken\",\"expiresAt\":1741341143}}"
                  }
                }
              }
            }
          },
          "403" : {
            "description" : "403",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "auth-controller-test/mismatch-info-fail-refresh-token" : {
                    "value" : "{\"httpStatus\":\"FORBIDDEN\",\"errorMessage\":\"유효하지 않은 토큰입니다.\"}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/auth/signup" : {
      "post" : {
        "tags" : [ "Auth API" ],
        "summary" : "회원가입 API",
        "description" : "사용자 정보를 입력받아 계정을 생성합니다.\n\n## 사용 예시\n이메일, 비밀번호, 닉네임을 입력하여 회원가입 진행\n\n## example\n1. 201 Created\n- 회원가입 성공\n2. 409 Conflict\n- 회원가입 실패: 중복 이메일 - 이미 등록된 이메일로 가입시도 시 예외발생",
        "operationId" : "auth-controller-test/",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "auth-controller-test/success-sign-up" : {
                  "value" : "{\"email\":\"test@mail.com\",\"password\":\"rawPassword123!\",\"nickName\":\"nickname\"}"
                },
                "auth-controller-test/duplicated-email-sign-up" : {
                  "value" : "{\"email\":\"test@mail.com\",\"password\":\"rawPassword123!\",\"nickName\":\"nickname\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "auth-controller-test/success-sign-up" : {
                    "value" : "{\"message\":\"회원가입이 완료되었습니다.\",\"data\":{\"id\":1,\"email\":\"test@mail.com\",\"nickname\":\"nickname\"}}"
                  }
                }
              }
            }
          },
          "409" : {
            "description" : "409",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "auth-controller-test/duplicated-email-sign-up" : {
                    "value" : "{\"httpStatus\":\"CONFLICT\",\"errorMessage\":\"이미 사용중인 이메일 입니다.\"}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/banners/{bannerId}" : {
      "get" : {
        "tags" : [ "Banner API" ],
        "summary" : "배너 단건 조회 API",
        "description" : "특정 배너의 상세 정보를 조회합니다.\n\n## 사용 예시\n배너 ID를 지정하여 상세 정보 조회\n\n## example\n1. 200 OK\n- 배너 단건 조회 성공: 배너 아이디로 배너 단건조회 성공\n2. 404 Not Found\n- 배너 단건 조회 실패: 존재하지 않는 배너아이디로 조회 - 존재하지 않는 배너아이디로 배너조회 시도 시 예외발생",
        "operationId" : "banner-controller-test/",
        "parameters" : [ {
          "name" : "bannerId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "banner-controller-test/invalid-banner-id-get-banner" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"배너가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "banner-controller-test/success-get-banner" : {
                    "value" : "{\"message\":\"배너 조회가 완료되었습니다.\",\"data\":{\"bannerId\":1,\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-11T00:00\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/carts/items" : {
      "post" : {
        "tags" : [ "Cart API", "api" ],
        "summary" : "장바구니 항목 추가 성공",
        "description" : "장바구니 항목 추가 성공",
        "operationId" : "cart",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "cart/addItem" : {
                  "value" : "{\n  \"productId\" : 1,\n  \"quantity\" : 3\n}"
                },
                "cart-controller-test/success-add-item" : {
                  "value" : "{\n  \"productId\" : 1,\n  \"quantity\" : 3\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cart/addItem" : {
                    "value" : "{\n  \"message\" : \"장바구니에 상품 추가가 완료되었습니다.\",\n  \"data\" : {\n    \"cartItemId\" : 1,\n    \"product\" : {\n      \"productId\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"독한 술입니다.\",\n      \"price\" : 120000,\n      \"stockQuantity\" : 50\n    },\n    \"quantity\" : 3\n  }\n}"
                  },
                  "cart-controller-test/success-add-item" : {
                    "value" : "{\n  \"message\" : \"장바구니에 상품 추가가 완료되었습니다.\",\n  \"data\" : {\n    \"cartItemId\" : 1,\n    \"product\" : {\n      \"productId\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"독한 술입니다.\",\n      \"price\" : 120000,\n      \"stockQuantity\" : 50\n    },\n    \"quantity\" : 3\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/cocktails/keyword" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "cocktail-controller-test/find-cocktails-by-keyword",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cocktail-controller-test/find-cocktails-by-keyword" : {
                    "value" : "{\n  \"message\" : \"칵테일 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    }, {\n      \"id\" : 2,\n      \"name\" : \"화이트 러시안\",\n      \"description\" : \"블랙 러시안에 우유를 더한 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 보드카, 깔루아를 빌드한다.\\n2.우유 혹은 생크림을 조심스레 붓는다.\\n3.살짝 저어준다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      }, {\n        \"id\" : 5,\n        \"name\" : \"우유\",\n        \"description\" : \"어릴 때 좀 많이 마실 걸\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 0.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 2,\n    \"size\" : 2,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 2,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/cocktails/popular" : {
      "get" : {
        "tags" : [ "api", "Cocktail API" ],
        "summary" : "인기 칵테일 조회",
        "description" : "인기 칵테일 조회",
        "operationId" : "cocktail",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cocktail-controller-test/get-popular-cocktails" : {
                    "value" : "{\n  \"message\" : \"칵테일 목록 조회가 완료되었습니다.\",\n  \"data\" : [ {\n    \"id\" : 1,\n    \"name\" : \"블랙 러시안\",\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }, {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  }, {\n    \"id\" : 2,\n    \"name\" : \"화이트 러시안\",\n    \"description\" : \"블랙 러시안에 우유를 더한 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 보드카, 깔루아를 빌드한다.\\n2.우유 혹은 생크림을 조심스레 붓는다.\\n3.살짝 저어준다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }, {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    }, {\n      \"id\" : 5,\n      \"name\" : \"우유\",\n      \"description\" : \"어릴 때 좀 많이 마실 걸\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 0.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  }, {\n    \"id\" : 3,\n    \"name\" : \"깔루아 밀크\",\n    \"description\" : \"달달구리한 커피 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    }, {\n      \"id\" : 5,\n      \"name\" : \"우유\",\n      \"description\" : \"어릴 때 좀 많이 마실 걸\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 0.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  } ]\n}"
                  },
                  "cocktail/getPopularCocktails" : {
                    "value" : "{\n  \"message\" : \"칵테일 목록 조회가 완료되었습니다.\",\n  \"data\" : [ {\n    \"id\" : 1,\n    \"name\" : \"블랙 러시안\",\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }, {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  }, {\n    \"id\" : 2,\n    \"name\" : \"화이트 러시안\",\n    \"description\" : \"블랙 러시안에 우유를 더한 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 보드카, 깔루아를 빌드한다.\\n2.우유 혹은 생크림을 조심스레 붓는다.\\n3.살짝 저어준다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }, {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    }, {\n      \"id\" : 5,\n      \"name\" : \"우유\",\n      \"description\" : \"어릴 때 좀 많이 마실 걸\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 0.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  }, {\n    \"id\" : 3,\n    \"name\" : \"깔루아 밀크\",\n    \"description\" : \"달달구리한 커피 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    }, {\n      \"id\" : 5,\n      \"name\" : \"우유\",\n      \"description\" : \"어릴 때 좀 많이 마실 걸\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 0.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  } ]\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/cocktails/search" : {
      "get" : {
        "tags" : [ "api", "Cocktail API" ],
        "summary" : "키워드를 통한 칵테일 검색",
        "description" : "키워드를 통한 칵테일 검색",
        "operationId" : "cocktail",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "기본값 : 1",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "기본값 : 10",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        }, {
          "name" : "isCraftable",
          "in" : "query",
          "description" : "기본값 : false",
          "required" : false,
          "schema" : {
            "type" : "boolean"
          }
        }, {
          "name" : "recipeType",
          "in" : "query",
          "description" : "OFFICIAL/CUSTOM",
          "required" : false,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "ingredientIds",
          "in" : "query",
          "description" : "List<Long>",
          "required" : false,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cocktail-controller-test/get-craftable-cocktail" : {
                    "value" : "{\n  \"message\" : \"칵테일 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    }, {\n      \"id\" : 2,\n      \"name\" : \"화이트 러시안\",\n      \"description\" : \"블랙 러시안에 우유를 더한 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 보드카, 깔루아를 빌드한다.\\n2.우유 혹은 생크림을 조심스레 붓는다.\\n3.살짝 저어준다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      }, {\n        \"id\" : 5,\n        \"name\" : \"우유\",\n        \"description\" : \"어릴 때 좀 많이 마실 걸\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 0.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 2,\n    \"size\" : 2,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 2,\n    \"empty\" : false\n  }\n}"
                  },
                  "cocktail-controller-test/get-cocktail-by-ingr" : {
                    "value" : "{\n  \"message\" : \"칵테일 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    }, {\n      \"id\" : 2,\n      \"name\" : \"화이트 러시안\",\n      \"description\" : \"블랙 러시안에 우유를 더한 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 보드카, 깔루아를 빌드한다.\\n2.우유 혹은 생크림을 조심스레 붓는다.\\n3.살짝 저어준다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      }, {\n        \"id\" : 5,\n        \"name\" : \"우유\",\n        \"description\" : \"어릴 때 좀 많이 마실 걸\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 0.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    }, {\n      \"id\" : 3,\n      \"name\" : \"깔루아 밀크\",\n      \"description\" : \"달달구리한 커피 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      }, {\n        \"id\" : 5,\n        \"name\" : \"우유\",\n        \"description\" : \"어릴 때 좀 많이 마실 걸\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 0.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 3,\n    \"size\" : 3,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 3,\n    \"empty\" : false\n  }\n}"
                  },
                  "cocktail/getCraftableCocktail" : {
                    "value" : "{\n  \"message\" : \"칵테일 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    }, {\n      \"id\" : 2,\n      \"name\" : \"화이트 러시안\",\n      \"description\" : \"블랙 러시안에 우유를 더한 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 보드카, 깔루아를 빌드한다.\\n2.우유 혹은 생크림을 조심스레 붓는다.\\n3.살짝 저어준다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      }, {\n        \"id\" : 5,\n        \"name\" : \"우유\",\n        \"description\" : \"어릴 때 좀 많이 마실 걸\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 0.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 2,\n    \"size\" : 2,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 2,\n    \"empty\" : false\n  }\n}"
                  },
                  "cocktail/getCocktailByIngr" : {
                    "value" : "{\n  \"message\" : \"칵테일 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    }, {\n      \"id\" : 2,\n      \"name\" : \"화이트 러시안\",\n      \"description\" : \"블랙 러시안에 우유를 더한 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 보드카, 깔루아를 빌드한다.\\n2.우유 혹은 생크림을 조심스레 붓는다.\\n3.살짝 저어준다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 1,\n        \"name\" : \"보드카\",\n        \"description\" : \"보드카\",\n        \"category\" : \"VODKA\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 40.0\n      }, {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      }, {\n        \"id\" : 5,\n        \"name\" : \"우유\",\n        \"description\" : \"어릴 때 좀 많이 마실 걸\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 0.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    }, {\n      \"id\" : 3,\n      \"name\" : \"깔루아 밀크\",\n      \"description\" : \"달달구리한 커피 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"OFFICIAL\",\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickname\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"ingredientList\" : [ {\n        \"id\" : 4,\n        \"name\" : \"깔루아 오리지널\",\n        \"description\" : \"커피 리큐르\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 16.0\n      }, {\n        \"id\" : 5,\n        \"name\" : \"우유\",\n        \"description\" : \"어릴 때 좀 많이 마실 걸\",\n        \"category\" : \"OTHER\",\n        \"imageUrl\" : \"대충 이미지 주소\",\n        \"avb\" : 0.0\n      } ],\n      \"likeCount\" : 0,\n      \"starRate\" : 0.0,\n      \"viewCount\" : 0,\n      \"createdAt\" : null,\n      \"modifiedAt\" : null\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 3,\n    \"size\" : 3,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 3,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/cocktails/{cocktailId}" : {
      "get" : {
        "tags" : [ "api", "Cocktail API" ],
        "operationId" : "cocktail",
        "parameters" : [ {
          "name" : "cocktailId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cocktail-controller-test/get-cocktail-by-id" : {
                    "value" : "{\n  \"message\" : \"칵테일 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"블랙 러시안\",\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }, {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  }\n}"
                  },
                  "cocktail/getCocktailById" : {
                    "value" : "{\n  \"message\" : \"칵테일 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"블랙 러시안\",\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }, {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "api", "Cocktail API" ],
        "summary" : "칵테일 삭제",
        "description" : "칵테일 삭제",
        "operationId" : "cocktail",
        "parameters" : [ {
          "name" : "cocktailId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cocktail-controller-test/delete-cocktail" : {
                    "value" : "{\n  \"message\" : \"칵테일 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "cocktail/deleteCocktail" : {
                    "value" : "{\n  \"message\" : \"칵테일 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api", "Cocktail API" ],
        "summary" : "칵테일 정보 수정",
        "description" : "칵테일 정보 수정",
        "operationId" : "cocktail",
        "parameters" : [ {
          "name" : "cocktailId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "cocktail-controller-test/update-cocktail" : {
                  "value" : "{\n  \"name\" : \"블랙 러시안\",\n  \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n  \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n  \"ingredientList\" : [ {\n    \"ingredientId\" : 1,\n    \"volume\" : \"60ml\"\n  }, {\n    \"ingredientId\" : 4,\n    \"volume\" : \"20ml\"\n  } ]\n}"
                },
                "cocktail/updateCocktail" : {
                  "value" : "{\n  \"name\" : \"블랙 러시안\",\n  \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n  \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n  \"ingredientList\" : [ {\n    \"ingredientId\" : 1,\n    \"volume\" : \"60ml\"\n  }, {\n    \"ingredientId\" : 4,\n    \"volume\" : \"20ml\"\n  } ]\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cocktail-controller-test/update-cocktail" : {
                    "value" : "{\n  \"message\" : \"칵테일 정보 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"블랙 러시안\",\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }, {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  }\n}"
                  },
                  "cocktail/updateCocktail" : {
                    "value" : "{\n  \"message\" : \"칵테일 정보 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"블랙 러시안\",\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n    \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n    \"type\" : \"OFFICIAL\",\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickname\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"ingredientList\" : [ {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }, {\n      \"id\" : 4,\n      \"name\" : \"깔루아 오리지널\",\n      \"description\" : \"커피 리큐르\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 16.0\n    } ],\n    \"likeCount\" : 0,\n    \"starRate\" : 0.0,\n    \"viewCount\" : 0,\n    \"createdAt\" : null,\n    \"modifiedAt\" : null\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/coupons/for-event" : {
      "get" : {
        "tags" : [ "Coupon API" ],
        "summary" : "이벤트용 쿠폰 목록 조회 API",
        "description" : "이벤트용 쿠폰 목록을 조회합니다.\n\n## 사용 예시\n이벤트 시작일, 종료일 등 조건을 지정하여 쿠폰 목록 조회\n\n## example\n1. 200 OK\n- 이벤트용 쿠폰 목록 조회 성공(쿠폰 만료일 기준 오름차순): 페이지네이션 기반 페이징, startDate, EndDate 미입력시 모든 쿠폰 반환\n- 이벤트용 쿠폰 목록 조회 성공: 시작일 입력 - 입력된 이벤트 시작일 이전에 시작하는 쿠폰목록 반환\n- 이벤트용 쿠폰 목록 조회 성공: 종료일 입력 - 입력된 이벤트 종료일 이후에 종료되는 쿠폰목록 반환\n- 이벤트용 쿠폰 목록 조회 성공: 이벤트 시작일 & 종료일 입력 - 입력된 이벤트 시작일 이전에 시작되고, 이벤트 종료일 이후에 종료되는 쿠폰목록 반환",
        "operationId" : "coupon-controller-test/success-get-coupons-for-event",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 번호",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "1"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지 크기",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "10"
          }
        }, {
          "name" : "eventStartDate",
          "in" : "query",
          "description" : "이벤트 시작일",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "2025-03-10"
          }
        }, {
          "name" : "eventEndDate",
          "in" : "query",
          "description" : "이벤트 종료일",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "2025-03-11"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-coupons-for-event-with-event-start-and-end-date" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"coupons\":[{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "coupon-controller-test/success-get-coupons-for-event" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"coupons\":[{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000},{\"id\":2,\"couponName\":\"2000원 할인쿠폰\",\"discountValue\":2000},{\"id\":3,\"couponName\":\"3000원 할인쿠폰\",\"discountValue\":3000}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "coupon-controller-test/success-get-coupons-for-event-with-event-start-date" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"coupons\":[{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000},{\"id\":3,\"couponName\":\"3000원 할인쿠폰\",\"discountValue\":3000}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "coupon-controller-test/success-get-coupons-for-event-with-event-end-date" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"coupons\":[{\"id\":3,\"couponName\":\"3000원 할인쿠폰\",\"discountValue\":3000}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/coupons/users" : {
      "get" : {
        "tags" : [ "api", "Coupon API" ],
        "summary" : "유저쿠폰 목록 조회 API",
        "description" : "로그인한 사용자의 쿠폰 목록을 조회합니다.\n\n## 사용 예시\n페이지 번호, 사이즈, 쿠폰 상태 등 조건을 지정하여 사용자 쿠폰 목록 조회\n\n## example\n1. 200 OK\n- 유저쿠폰 목록 조회 성공(쿠폰 만료일 기준 오름차순): 페이지 기반 페이지네이션, 로그인한 유저가 본인의 쿠폰 목록 조회, 상태 미입력시 모든 쿠폰 반환\n- 유저쿠폰 목록 조회 성공: 빈 목록 조회 - 조건과 일치하는 쿠폰이 없을 시 빈 목록 반환\n- 유저쿠폰 목록 조회 성공: ISSUED 상태 조회 - 쿠폰 상태가 issued인 쿠폰 반환",
        "operationId" : "coupon-controller-test/success-get-",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 번호",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "1"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지 크기",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : "10"
          }
        }, {
          "name" : "status",
          "in" : "query",
          "description" : "쿠폰 상태",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : "issued"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-empty-user-coupons-with-issued" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"userCoupons\" : [ {\n      \"userCouponId\" : 1,\n      \"userId\" : 1,\n      \"coupon\" : {\n        \"id\" : 1,\n        \"couponName\" : \"1000원 할인쿠폰\",\n        \"discountValue\" : 1000\n      },\n      \"status\" : \"ISSUED\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-user-coupons" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"userCoupons\":[{\"userCouponId\":1,\"userId\":1,\"coupon\":{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000},\"status\":\"ISSUED\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "coupon-controller-test/success-get-empty-user-coupons" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"userCoupons\":[],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  },
                  "coupon-controller-test/success-get-user-coupons-with-issued" : {
                    "value" : "{\"message\":\"쿠폰 목록 조회가 완료되었습니다.\",\"data\":{\"userCoupons\":[{\"userCouponId\":1,\"userId\":1,\"coupon\":{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000},\"status\":\"ISSUED\"}],\"currentPage\":0,\"totalPages\":1,\"hasNext\":false}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/coupons/{couponId}" : {
      "get" : {
        "tags" : [ "Coupon API" ],
        "summary" : "쿠폰 단건 조회 API",
        "description" : "특정 쿠폰의 상세 정보를 조회합니다.\n\n## 사용 예시\n쿠폰 ID를 지정하여 쿠폰 정보 조회\n\n## example\n1. 200 OK\n- 쿠폰 단건 조회 성공: 쿠폰 아이디로 쿠폰 정보 조회\n2. 404 Not Found\n- 쿠폰 단건 조회 실패: 존재하지 않는 쿠폰 아이디로 조회 시도 - 존재하지 않는 쿠폰 아이디로 조회 시도 시 예외발생",
        "operationId" : "coupon-controller-test/",
        "parameters" : [ {
          "name" : "couponId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "coupon-controller-test/invalid-coupon-id-get-coupon" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"쿠폰이 존재하지 않습니다\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-coupon" : {
                    "value" : "{\"message\":\"쿠폰 조회가 완료되었습니다.\",\"data\":{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000,\"startTime\":\"2025-03-01T00:00\",\"endTime\":\"2025-03-10T00:00\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/events/{eventId}" : {
      "get" : {
        "tags" : [ "api", "Event API" ],
        "summary" : "이벤트 단건 조회 API",
        "description" : "특정 이벤트의 상세 정보를 조회합니다.\n\n## 사용 예시\n이벤트 ID를 지정하여 이벤트 상세 정보 조회\n\n## example\n1. 200 OK\n- 이벤트 단건 조회 성공: 이벤트 아이디로 이벤트 단건 조회\n2. 404 Not Found\n- 이벤트 단건 조회 실패: 존재하지 않는 이벤트 아이디로 조회 - 존재하지 않는 이벤트 아이디로 이벤트 단건 조회 시 예외 발생\n3. 400 Bad Request\n- 이벤트 단건 조회 실패: 이벤트 디테일 String -> JsonNode 파싱 실패 - 이벤트 단건 조회 시, 응답을 jsonNode로 반환하기 위해 String을 파싱할 때 실패하는 경우 예외 발생",
        "operationId" : "event",
        "parameters" : [ {
          "name" : "eventId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event-controller-test/success-get-event" : {
                    "value" : "{\n  \"message\" : \"이벤트 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"상품 증정 선착순 이벤트\",\n    \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n    \"startTime\" : \"2025-03-08T18:00\",\n    \"endTime\" : \"2025-03-09T19:00\",\n    \"eventDetail\" : {\n      \"couponName\" : \"20% 할인 쿠폰\",\n      \"couponId\" : 1\n    }\n  }\n}"
                  },
                  "event/success-get-event" : {
                    "value" : "{\"message\":\"이벤트 조회가 완료되었습니다.\",\"data\":{\"id\":1,\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startTime\":\"2025-03-08T18:00\",\"endTime\":\"2025-03-09T19:00\",\"eventDetail\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event-controller-test/invalid-event-id-get-event" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"이벤트가 존재하지 않습니다.\"\n}"
                  },
                  "event/invalid-event-id-get-event" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"이벤트가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event-controller-test/fail-parsing-json-node-get-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"JSON 데이터 검증 중 오류가 발생했습니다.\"\n}"
                  },
                  "event/fail-parsing-json-node-get-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"JSON 데이터 검증 중 오류가 발생했습니다.\"}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/favorites/{favoriteId}" : {
      "delete" : {
        "tags" : [ "api", "Favorite API" ],
        "summary" : "즐겨찾기 삭제 API",
        "description" : "즐겨찾기 삭제 API",
        "operationId" : "favorite",
        "parameters" : [ {
          "name" : "favoriteId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "403" : {
            "description" : "403",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "favorite-controller-test/invalid-id-delete-favorite" : {
                    "value" : "{\n  \"httpStatus\" : \"FORBIDDEN\",\n  \"errorMessage\" : \"접근 권한이 없습니다.\"\n}"
                  },
                  "favorite/invalid-id-delete-favorite" : {
                    "value" : "{\"httpStatus\":\"FORBIDDEN\",\"errorMessage\":\"접근 권한이 없습니다.\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-delete-favorite" : {
                    "value" : "{\n  \"message\" : \"즐겨찾기 해제가 완료되었습니다.\",\n  \"data\" : {\n    \"cocktailId\" : 1,\n    \"name\" : \"블랙 러시안\",\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n    \"type\" : \"OFFICIAL\",\n    \"favoritedAt\" : \"2025-03-10T01:01:00\"\n  }\n}"
                  },
                  "favorite/success-delete-favorite" : {
                    "value" : "{\"message\":\"즐겨찾기 해제가 완료되었습니다.\",\"data\":{\"cocktailId\":1,\"name\":\"블랙 러시안\",\"description\":\"보드카와 깔루아로 만드는 칵테일\",\"type\":\"OFFICIAL\",\"favoritedAt\":\"2025-03-10T01:01:00\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/ingredients/search" : {
      "get" : {
        "tags" : [ "api", "Ingredient_API" ],
        "summary" : "재료 검색 성공",
        "description" : "사용자가 검색 키워드와 카테고리를 입력하여 재료를 검색할 수 있는 API",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "keyword",
          "in" : "query",
          "description" : "검색할 재료 키워드(선택)",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : " "
          }
        }, {
          "name" : "category",
          "in" : "query",
          "description" : "재료 카테고리(선택)",
          "required" : true,
          "schema" : {
            "type" : "string",
            "default" : " "
          }
        }, {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 번호 (1부터 시작, 선택)",
          "required" : true,
          "schema" : {
            "type" : "integer",
            "format" : "int32",
            "default" : 1
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "한 페이지당 조회할 개수(선택)",
          "required" : true,
          "schema" : {
            "type" : "integer",
            "format" : "int32",
            "default" : 10
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredient-controller-test/조건별_재료조회_성공" : {
                    "value" : "{\n  \"message\" : \"재료 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"id\" : 6,\n      \"name\" : \"golden\",\n      \"description\" : \"맛있는거\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 0.0\n    }, {\n      \"id\" : 7,\n      \"name\" : \"깔루아\",\n      \"description\" : \"golden --\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 0.0\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 2,\n    \"size\" : 2,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 2,\n    \"empty\" : false\n  }\n}"
                  },
                  "ingredient/search" : {
                    "value" : "{\n  \"message\" : \"재료 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"id\" : 6,\n      \"name\" : \"golden\",\n      \"description\" : \"맛있는거\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 0.0\n    }, {\n      \"id\" : 7,\n      \"name\" : \"깔루아\",\n      \"description\" : \"golden --\",\n      \"category\" : \"OTHER\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 0.0\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 2,\n    \"size\" : 2,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 2,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/ingredients/{ingredientId}" : {
      "get" : {
        "tags" : [ "api", "Ingredient_API" ],
        "summary" : "재료 단건 조회 성공 ",
        "description" : "재료 단건 조회에 성공합니다.",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "ingredientId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료_단건조회_성공" : {
                    "value" : "{\n  \"message\" : \"재료 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"보드카\",\n    \"category\" : \"VODKA\",\n    \"imageUrl\" : \"대충 이미지 주소\",\n    \"avb\" : 40.0\n  }\n}"
                  },
                  "ingredient/get" : {
                    "value" : "{\n  \"message\" : \"재료 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"보드카\",\n    \"category\" : \"VODKA\",\n    \"imageUrl\" : \"대충 이미지 주소\",\n    \"avb\" : 40.0\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "api", "Ingredient_API" ],
        "summary" : "재료 삭제 성공",
        "description" : "재료 삭제 성공",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "ingredientId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료삭제_성공" : {
                    "value" : "{\n  \"message\" : \"재료 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "ingredient/delete" : {
                    "value" : "{\n  \"message\" : \"재료 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api", "Ingredient_API" ],
        "summary" : "재료 수정 성공",
        "description" : "재료 수정 성공",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "ingredientId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "ingredient-controller-test/재료수정_성공" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"보드카\",\n  \"category\" : \"VODKA\",\n  \"avb\" : 40.0\n}"
                },
                "ingredient/update" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"보드카\",\n  \"category\" : \"VODKA\",\n  \"avb\" : 40.0\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료수정_성공" : {
                    "value" : "{\n  \"message\" : \"재료 정보 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"보드카\",\n    \"category\" : \"VODKA\",\n    \"imageUrl\" : \"url\",\n    \"avb\" : 40.0\n  }\n}"
                  },
                  "ingredient/update" : {
                    "value" : "{\n  \"message\" : \"재료 정보 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"보드카\",\n    \"category\" : \"VODKA\",\n    \"imageUrl\" : \"url\",\n    \"avb\" : 40.0\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/orders/{orderId}" : {
      "get" : {
        "tags" : [ "Order API", "api" ],
        "summary" : "주문 단건 조회 성공",
        "description" : "주문 단건 조회 성공",
        "operationId" : "order",
        "parameters" : [ {
          "name" : "orderId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "order/getOrder" : {
                    "value" : "{\n  \"message\" : \"주문 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"orderId\" : 1,\n    \"orderNumber\" : \"23031114553289\",\n    \"amount\" : 120000,\n    \"orderItems\" : [ {\n      \"productName\" : \"보드카\",\n      \"quantity\" : 3,\n      \"price\" : 120000\n    } ]\n  }\n}"
                  },
                  "order-controller-test/success-get-order" : {
                    "value" : "{\n  \"message\" : \"주문 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"orderId\" : 1,\n    \"orderNumber\" : \"23031114553289\",\n    \"amount\" : 120000,\n    \"orderItems\" : [ {\n      \"productName\" : \"보드카\",\n      \"quantity\" : 3,\n      \"price\" : 120000\n    } ]\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "Order API", "api" ],
        "summary" : "주문 제거 성공",
        "description" : "주문 제거 성공",
        "operationId" : "order",
        "parameters" : [ {
          "name" : "orderId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "order/deleteOrder" : {
                    "value" : "{\n  \"message\" : \"주문이 취소되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "order-controller-test/success-delete-order" : {
                    "value" : "{\n  \"message\" : \"주문이 취소되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "Order API", "api" ],
        "summary" : "주문 수정 성공",
        "description" : "주문 수정 성공",
        "operationId" : "order",
        "parameters" : [ {
          "name" : "orderId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "order/updateOrder" : {
                  "value" : "{\n  \"orderStatus\" : \"PROCESSING\"\n}"
                },
                "order-controller-test/success-update-order" : {
                  "value" : "{\n  \"orderStatus\" : \"PROCESSING\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "order/updateOrder" : {
                    "value" : "{\n  \"message\" : \"주문 상태가 변경되었습니다.\",\n  \"data\" : {\n    \"orderId\" : 1,\n    \"name\" : \"보드카 외 2개\",\n    \"amount\" : 120000,\n    \"status\" : \"PENDING_PAYMENT\",\n    \"updateDate\" : \"2025-03-10T16:08:17.783333\"\n  }\n}"
                  },
                  "order-controller-test/success-update-order" : {
                    "value" : "{\n  \"message\" : \"주문 상태가 변경되었습니다.\",\n  \"data\" : {\n    \"orderId\" : 1,\n    \"name\" : \"보드카 외 2개\",\n    \"amount\" : 120000,\n    \"status\" : \"PENDING_PAYMENT\",\n    \"updateDate\" : \"2025-03-10T16:08:17.783333\"\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/pantries/ingredients" : {
      "delete" : {
        "tags" : [ "Pantry_API", "api" ],
        "summary" : "펜트리 내의 재료 삭제 API",
        "description" : "사용자의 펜트리에서 재료를 삭제합니다",
        "operationId" : "pantry",
        "parameters" : [ {
          "name" : "ingredientIds",
          "in" : "query",
          "description" : "삭제할 재료의 ID 리스트 ",
          "required" : true,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        } ],
        "responses" : {
          "204" : {
            "description" : "204",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "pantry/removeIngredient" : {
                    "value" : "{\n  \"message\" : \"재료 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "pantry-controller-test/선택_재료_펜트리에서제외_성공" : {
                    "value" : "{\n  \"message\" : \"재료 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/payments/confirm" : {
      "post" : {
        "tags" : [ "Payment API", "api" ],
        "summary" : "결제 승인 성공",
        "description" : "결제 승인 성공",
        "operationId" : "payment",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "payment/confirmPayment" : {
                  "value" : "{\n  \"orderId\" : \"보드카 외 2개\",\n  \"amount\" : 120000,\n  \"paymentKey\" : \"some-payment-key\"\n}"
                },
                "payment-controller-test/success-confirm-payment" : {
                  "value" : "{\n  \"orderId\" : \"보드카 외 2개\",\n  \"amount\" : 120000,\n  \"paymentKey\" : \"some-payment-key\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "payment/confirmPayment" : {
                    "value" : "{\n  \"message\" : \"결제가 정상적으로 처리되었습니다.\",\n  \"data\" : {\n    \"paymentKey\" : \"some-payment-key\",\n    \"status\" : \"DONE\",\n    \"orderId\" : \"23031114553289\",\n    \"orderName\" : \"보드카 외 2개\",\n    \"totalAmount\" : 120000\n  }\n}"
                  },
                  "payment-controller-test/success-confirm-payment" : {
                    "value" : "{\n  \"message\" : \"결제가 정상적으로 처리되었습니다.\",\n  \"data\" : {\n    \"paymentKey\" : \"some-payment-key\",\n    \"status\" : \"DONE\",\n    \"orderId\" : \"23031114553289\",\n    \"orderName\" : \"보드카 외 2개\",\n    \"totalAmount\" : 120000\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/payments/{paymentId}" : {
      "get" : {
        "tags" : [ "Payment API", "api" ],
        "summary" : "결제 조회 성공",
        "description" : "결제 조회 성공",
        "operationId" : "payment",
        "parameters" : [ {
          "name" : "paymentId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "payment/findPayment" : {
                    "value" : "{\n  \"message\" : \"결제 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"paymentKey\" : \"some-payment-key\",\n    \"status\" : \"DONE\",\n    \"orderId\" : \"23031114553289\",\n    \"orderName\" : \"보드카 외 2개\",\n    \"totalAmount\" : 120000\n  }\n}"
                  },
                  "payment-controller-test/success-find-payment" : {
                    "value" : "{\n  \"message\" : \"결제 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"paymentKey\" : \"some-payment-key\",\n    \"status\" : \"DONE\",\n    \"orderId\" : \"23031114553289\",\n    \"orderName\" : \"보드카 외 2개\",\n    \"totalAmount\" : 120000\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/products/{productId}" : {
      "get" : {
        "tags" : [ "api", "Product API" ],
        "summary" : "상품 단건 조회 성공",
        "description" : "상품 단건 조회 성공",
        "operationId" : "product",
        "parameters" : [ {
          "name" : "productId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "product-controller-test/success-get-product" : {
                    "value" : "{\n  \"message\" : \"상품 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"productId\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"독한 술입니다.\",\n    \"price\" : 120000,\n    \"stockQuantity\" : 50\n  }\n}"
                  },
                  "product/getProduct" : {
                    "value" : "{\n  \"message\" : \"상품 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"productId\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"독한 술입니다.\",\n    \"price\" : 120000,\n    \"stockQuantity\" : 50\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "api", "Product API" ],
        "summary" : "상품 제거 성공",
        "description" : "상품 제거 성공",
        "operationId" : "product",
        "parameters" : [ {
          "name" : "productId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "product-controller-test/success-delete-product" : {
                    "value" : "{\n  \"message\" : \"상품 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "product/deleteProduct" : {
                    "value" : "{\n  \"message\" : \"상품 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api", "Product API" ],
        "summary" : "상품 수정 성공",
        "description" : "상품 수정 성공",
        "operationId" : "product",
        "parameters" : [ {
          "name" : "productId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "product-controller-test/success-update-product" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"독한 술입니다.\",\n  \"category\" : \"ALCOHOL\",\n  \"price\" : 120000,\n  \"stockQuantity\" : 50,\n  \"status\" : \"SALE\"\n}"
                },
                "product/updateProduct" : {
                  "value" : "{\n  \"name\" : \"보드카\",\n  \"description\" : \"독한 술입니다.\",\n  \"category\" : \"ALCOHOL\",\n  \"price\" : 120000,\n  \"stockQuantity\" : 50,\n  \"status\" : \"SALE\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "product-controller-test/success-update-product" : {
                    "value" : "{\n  \"message\" : \"상품 정보 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"productId\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"독한 술입니다.\",\n    \"price\" : 120000,\n    \"stockQuantity\" : 50\n  }\n}"
                  },
                  "product/updateProduct" : {
                    "value" : "{\n  \"message\" : \"상품 정보 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"productId\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"독한 술입니다.\",\n    \"price\" : 120000,\n    \"stockQuantity\" : 50\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/banners/{bannerId}" : {
      "delete" : {
        "tags" : [ "Banner API" ],
        "summary" : "ADMIN 배너 삭제 API",
        "description" : "관리자가 배너를 삭제합니다.\n\n## 사용 예시\n배너 ID를 지정하여 배너 삭제\n\n## example\n1. 200 OK\n- ADMIN 배너 삭제 성공: 관리자가 배너아이디로 배너 삭제\n2. 404 Not Found\n- ADMIN 배너 삭제 실패: 존재하지 않는 배너아이디로 삭제 - 관리자가 존재하지않는 배너아이디로 배너삭제 시도 시 예외발생",
        "operationId" : "admin-banner-controller-test/",
        "parameters" : [ {
          "name" : "bannerId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/invalid-banner-id-delete-banner" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"배너가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/success-delete-banner" : {
                    "value" : "{\"message\":\"배너 삭제가 완료되었습니다.\",\"data\":1}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "Banner API" ],
        "summary" : "ADMIN 배너 수정 API",
        "description" : "관리자가 기존 배너를 수정합니다.\n\n## 사용 예시\n배너 ID를 지정하여 이벤트 ID, 이미지 URL, 시작 시간, 종료 시간 정보를 수정\n\n## example\n1. 200 OK\n- ADMIN 배너 수정 성공: 관리자가 배너 아이디로 배너정보 수정\n2. 404 Not Found\n- ADMIN 배너 수정 실패: 존재하지 않는 배너아이디로 수정 - 관리자가 존재하지 않는 배너아이디로 배너정보 수정 시도 시 예외발생\n- ADMIN 배너 수정 실패: 존재하지 않는 이벤트아이디로 수정 - 관리자가 존재하지 않는 이벤트아이디로 배너정보 수정 시도시 예외발생",
        "operationId" : "admin-banner-controller-test/",
        "parameters" : [ {
          "name" : "bannerId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "admin-banner-controller-test/invalid-event-id-update-banner" : {
                  "value" : "{\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\"}"
                },
                "admin-banner-controller-test/invalid-banner-id-update-banner" : {
                  "value" : "{\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\"}"
                },
                "admin-banner-controller-test/success-update-banner" : {
                  "value" : "{\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/invalid-event-id-update-banner" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"이벤트가 존재하지 않습니다.\"}"
                  },
                  "admin-banner-controller-test/invalid-banner-id-update-banner" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"배너가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/success-update-banner" : {
                    "value" : "{\"message\":\"배너 수정이 완료되었습니다.\",\"data\":{\"bannerId\":1,\"eventId\":1,\"imageUrl\":\"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\"startTime\":\"2025-03-09T18:00\",\"endTime\":\"2025-03-11T00:00\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/chats/{userId}" : {
      "get" : {
        "tags" : [ "Chat API", "api" ],
        "summary" : "어드민용 채팅 조회 성공",
        "description" : "어드민용 채팅 조회 성공",
        "operationId" : "chat",
        "parameters" : [ {
          "name" : "userId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "chat/findChatForAdmin" : {
                    "value" : "{\n  \"messageList\" : [ {\n    \"sender\" : \"user\",\n    \"content\" : \"메시지 입니다.\",\n    \"timeMillis\" : 1741314450785\n  } ]\n}"
                  },
                  "chat-controller-test/success-find-chat-for-admin" : {
                    "value" : "{\n  \"messageList\" : [ {\n    \"sender\" : \"user\",\n    \"content\" : \"메시지 입니다.\",\n    \"timeMillis\" : 1741314450785\n  } ]\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/coupons/{couponId}" : {
      "delete" : {
        "tags" : [ "Coupon API" ],
        "summary" : "ADMIN 쿠폰 삭제 API",
        "description" : "관리자가 쿠폰을 삭제합니다.\n\n## 사용 예시\n쿠폰 ID를 지정하여 쿠폰 삭제\n\n## example\n1. 200 OK\n- ADMIN 쿠폰 삭제 성공: 관리자가 쿠폰 삭제 성공\n2. 404 Not Found\n- ADMIN 쿠폰 삭제 실패: 존재하지 않는 쿠폰아이디로 삭제 시도 - 관리자가 존재하지 않는 쿠폰아이디로 쿠폰 삭제 시도 시 예외발생",
        "operationId" : "admin-coupon-controller-test/",
        "parameters" : [ {
          "name" : "couponId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/invalid-coupon-id-delete-coupon" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"쿠폰이 존재하지 않습니다\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-delete-coupon" : {
                    "value" : "{\"message\":\"쿠폰 삭제가 완료되었습니다.\",\"data\":1}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "Coupon API" ],
        "summary" : "ADMIN 쿠폰 수정 API",
        "description" : "관리자가 기존 쿠폰을 수정합니다.\n\n## 사용 예시\n쿠폰 ID를 지정하여 쿠폰명, 할인 값, 시작 시간, 종료 시간 등 정보를 수정\n\n## example\n1. 200 OK\n- ADMIN 쿠폰 수정 성공: 관리자가 쿠폰 정보 수정\n2. 404 Not Found\n- ADMIN 쿠폰 수정 실패: 존재하지 않는 쿠폰아이디로 수정 시도 - 관리자가 존재하지 않는 쿠폰아이디로 수정 시도 시 예외발생",
        "operationId" : "admin-coupon-controller-test/",
        "parameters" : [ {
          "name" : "couponId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "admin-coupon-controller-test/invalid-coupon-id-update-coupon" : {
                  "value" : "{\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000,\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\"}"
                },
                "admin-coupon-controller-test/success-update-coupon" : {
                  "value" : "{\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000,\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/invalid-coupon-id-update-coupon" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"쿠폰이 존재하지 않습니다\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-update-coupon" : {
                    "value" : "{\"message\":\"쿠폰 수정이 완료되었습니다.\",\"data\":{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000,\"startTime\":\"2025-03-01T00:00\",\"endTime\":\"2025-03-10T00:00\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/deliveries/{deliveryId}" : {
      "get" : {
        "tags" : [ "api", "Delivery API" ],
        "summary" : "ADMIN 배송 정보 조회 API",
        "description" : "관리자가 배송 정보를 조회합니다.\n\n## 사용 예시\n배송 ID를 지정하여 배송 정보 조회\n\n## example\n1. 200 OK\n- ADMIN 배송 정보 조회 성공: 관리자가 배송 아이디로 배송 정보 단건 조회\n2. 404 Not Found\n- ADMIN 배송 정보 조회 실패: 존재하지 않는 배송 아이디로 조회 - 관리자가 존재하지 않는 배송 아이디로 배송정보 조회 시도 시 예외 발생",
        "operationId" : "admin-delivery-controller-test/invalid-delivery-id-get-deliveryadmin-delivery-controller-test/success-get-deliverydelivery/invalid-delivery-id-get-deliverydelivery/success-get-delivery",
        "parameters" : [ {
          "name" : "deliveryId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-delivery-id-get-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"배송 정보가 존재하지 않습니다.\"\n}"
                  },
                  "delivery/invalid-delivery-id-get-delivery" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"배송 정보가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/success-get-delivery" : {
                    "value" : "{\n  \"message\" : \"배송 정보 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"deliveryId\" : 2,\n    \"orderId\" : 1,\n    \"receiverName\" : \"봉미선\",\n    \"receiverPhone\" : \"010-1234-4567\",\n    \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n    \"courierCompany\" : \"CJGLS\",\n    \"trackingNumber\" : \"123456789\",\n    \"status\" : \"REGISTERED\",\n    \"createdAt\" : \"2025-03-11T00:00:00\"\n  }\n}"
                  },
                  "delivery/success-get-delivery" : {
                    "value" : "{\"message\":\"배송 정보 조회가 완료되었습니다.\",\"data\":{\"deliveryId\":2,\"orderId\":1,\"receiverName\":\"봉미선\",\"receiverPhone\":\"010-1234-4567\",\"deliveryMessage\":\"현관 비밀번호는 #0000#입니다.\",\"courierCompany\":\"CJGLS\",\"trackingNumber\":\"123456789\",\"status\":\"REGISTERED\",\"createdAt\":\"2025-03-11T00:00:00\"}}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api", "Delivery API" ],
        "summary" : "ADMIN 배송 정보 수정 API",
        "description" : "관리자가 배송 정보를 수정합니다.\n\n## 사용 예시\n배송 ID를 지정하여 배송 상태 등 정보 수정\n\n## example\n1. 200 OK\n- ADMIN 배송 정보 수정 성공: 관리자가 배송 정보 아이디로 배송 정보 수정\n2. 404 Not Found\n- ADMIN 배송 정보 수정 실패: 존재하지 않는 배송 아이디 수정 - 관리자가 존재하지 않는 배송 아이디로 배송 정보 수정시도 시 예외 발생\n3. 400 Bad Request\n- ADMIN 배송 정보 수정 실패: 같은 배송 상태로 변경 시도 - 관리자가 배송 정보 수정 시 같은 배송 상태로 변경할 경우 예외 발생\n- ADMIN 배송 정보 수정 실패: 유효하지 않은 순서로 상태 변경 시도 - 관리자가 배송 정보 수정 시 유효하지 않은 순서로 상태를 변경할 경우 예외 발생",
        "operationId" : "admin-delivery-controller-test/conflict-delivery-status-update-deliveryadmin-delivery-controller-test/invalid-delivery-id-update-deliveryadmin-delivery-controller-test/invalid-delivery-status-update-deliveryadmin-delivery-controller-test/success-update-deliverydelivery/conflict-delivery-status-update-deliverydelivery/invalid-delivery-id-update-deliverydelivery/invalid-delivery-status-update-deliverydelivery/success-update-delivery",
        "parameters" : [ {
          "name" : "deliveryId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "admin-delivery-controller-test/invalid-delivery-status-update-delivery" : {
                  "value" : "{\n  \"status\" : \"registered\"\n}"
                },
                "admin-delivery-controller-test/invalid-delivery-id-update-delivery" : {
                  "value" : "{\n  \"status\" : \"registered\"\n}"
                },
                "admin-delivery-controller-test/conflict-delivery-status-update-delivery" : {
                  "value" : "{\n  \"status\" : \"registered\"\n}"
                },
                "admin-delivery-controller-test/success-update-delivery" : {
                  "value" : "{\n  \"status\" : \"registered\"\n}"
                },
                "delivery/invalid-delivery-status-update-delivery" : {
                  "value" : "{\"status\":\"registered\"}"
                },
                "delivery/invalid-delivery-id-update-delivery" : {
                  "value" : "{\"status\":\"registered\"}"
                },
                "delivery/conflict-delivery-status-update-delivery" : {
                  "value" : "{\"status\":\"registered\"}"
                },
                "delivery/success-update-delivery" : {
                  "value" : "{\"status\":\"registered\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-delivery-status-update-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"유효하지 않은 배송 상태 변경입니다\"\n}"
                  },
                  "admin-delivery-controller-test/conflict-delivery-status-update-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"이미 해당 배송 상태입니다\"\n}"
                  },
                  "delivery/invalid-delivery-status-update-delivery" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"유효하지 않은 배송 상태 변경입니다\"}"
                  },
                  "delivery/conflict-delivery-status-update-delivery" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"이미 해당 배송 상태입니다\"}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-delivery-id-update-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"배송 정보가 존재하지 않습니다.\"\n}"
                  },
                  "delivery/invalid-delivery-id-update-delivery" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"배송 정보가 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/success-update-delivery" : {
                    "value" : "{\n  \"message\" : \"배송 정보 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"deliveryId\" : 2,\n    \"orderId\" : 1,\n    \"receiverName\" : \"봉미선\",\n    \"receiverPhone\" : \"010-1234-4567\",\n    \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n    \"courierCompany\" : \"CJGLS\",\n    \"trackingNumber\" : \"123456789\",\n    \"status\" : \"REGISTERED\",\n    \"createdAt\" : \"2025-03-11T00:00:00\"\n  }\n}"
                  },
                  "delivery/success-update-delivery" : {
                    "value" : "{\"message\":\"배송 정보 수정이 완료되었습니다.\",\"data\":{\"deliveryId\":2,\"orderId\":1,\"receiverName\":\"봉미선\",\"receiverPhone\":\"010-1234-4567\",\"deliveryMessage\":\"현관 비밀번호는 #0000#입니다.\",\"courierCompany\":\"CJGLS\",\"trackingNumber\":\"123456789\",\"status\":\"REGISTERED\",\"createdAt\":\"2025-03-11T00:00:00\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/events/{eventId}" : {
      "delete" : {
        "tags" : [ "Event API", "api" ],
        "summary" : "ADMIN 이벤트 삭제 API",
        "description" : "관리자가 이벤트를 삭제합니다.\n\n## 사용 예시\n이벤트 ID를 지정하여 이벤트 삭제\n\n## example\n1. 200 OK\n- ADMIN 이벤트 삭제 성공: 관리자가 이벤트 아이디로 이벤트 삭제\n2. 404 Not Found\n- ADMIN 이벤트 삭제 실패: 존재하지 않는 이벤트아이디로 삭제시도 - 관리자가 존재하지 않는 이벤트아이이디로 삭제 시도 시 예외 발생",
        "operationId" : "admin-event-controller-test/invalid-event-id-delete-eventadmin-event-controller-test/success-delete-eventevent/invalid-event-id-delete-eventevent/success-delete-event",
        "parameters" : [ {
          "name" : "eventId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event/success-delete-event" : {
                    "value" : "{\"message\":\"이벤트 삭제가 완료되었습니다.\",\"data\":1}"
                  },
                  "admin-event-controller-test/success-delete-event" : {
                    "value" : "{\n  \"message\" : \"이벤트 삭제가 완료되었습니다.\",\n  \"data\" : 1\n}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event/invalid-event-id-delete-event" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"이벤트가 존재하지 않습니다.\"}"
                  },
                  "admin-event-controller-test/invalid-event-id-delete-event" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"이벤트가 존재하지 않습니다.\"\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "Event API", "api" ],
        "summary" : "ADMIN 이벤트 수정 API",
        "description" : "관리자가 기존 이벤트를 수정합니다.\n\n## 사용 예시\n이벤트 ID를 지정하여 이벤트명, 설명, 시작 시간, 종료 시간, 이벤트 상세 정보 등 수정\n\n## example\n1. 200 OK\n- ADMIN 이벤트 수정 성공: 관리자가 이벤트 아이디로 이벤트 정보 수정\n2. 404 Not Found\n- ADMIN 이벤트 수정 실패: 이벤트가 등록된 배너 정보가 존재하지 않을 경우 - 관리자가 이벤트 수정 시 이벤트가 등록된 배너 정보가 존재하지 않을 경우 예외 발생\n- ADMIN 이벤트 수정 실패: 쿠폰 정보 누락 - 관리자가 이벤트 수정 시 입력한 이벤트 디테일 Object에 쿠폰정보가 누락된 경우 예외 발생\n- ADMIN 이벤트 수정 실패: 이벤트 디테일 Object -> String 파싱 실패 - 관리자가 이벤트 수정 시 이벤트 디테일 Object를 String으로 파싱 실패할 경우 예외 발생\n- ADMIN 이벤트 수정 실패: 이벤트 디테일 String -> JsonNode 파싱 실패 - 관리자가 이벤트 수정 시 수정 결과를 반환하기 위해 이벤트 디테일 String을 JsonNode로 파싱할 때, 실패하는 경우 예외 발생",
        "operationId" : "admin-event-controller-test/fail-parsing-json-node-update-eventadmin-event-controller-test/fail-parsing-update-eventadmin-event-controller-test/invalid-banner-id-update-eventadmin-event-controller-test/missing-details-update-eventadmin-event-controller-test/success-update-eventevent/fail-parsing-json-node-update-eventevent/fail-parsing-update-eventevent/invalid-banner-id-update-eventevent/missing-details-update-eventevent/success-update-event",
        "parameters" : [ {
          "name" : "eventId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "event/invalid-banner-id-update-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "event/success-update-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "event/missing-details-update-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "event/fail-parsing-json-node-update-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "event/fail-parsing-update-event" : {
                  "value" : "{\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startDate\":\"2025-04-28\",\"startTime\":\"16:58\",\"endDate\":\"2025-05-04\",\"endTime\":\"16:58\",\"eventType\":\"fcfs\",\"limitCount\":10,\"eventDetailData\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}"
                },
                "admin-event-controller-test/invalid-banner-id-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-04-28\",\n  \"startTime\" : \"16:58\",\n  \"endDate\" : \"2025-05-04\",\n  \"endTime\" : \"16:58\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponName\" : \"20% 할인 쿠폰\",\n    \"couponId\" : 1\n  }\n}"
                },
                "admin-event-controller-test/success-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-04-28\",\n  \"startTime\" : \"16:58\",\n  \"endDate\" : \"2025-05-04\",\n  \"endTime\" : \"16:58\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponName\" : \"20% 할인 쿠폰\",\n    \"couponId\" : 1\n  }\n}"
                },
                "admin-event-controller-test/missing-details-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-04-28\",\n  \"startTime\" : \"16:58\",\n  \"endDate\" : \"2025-05-04\",\n  \"endTime\" : \"16:58\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponName\" : \"20% 할인 쿠폰\",\n    \"couponId\" : 1\n  }\n}"
                },
                "admin-event-controller-test/fail-parsing-json-node-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-04-28\",\n  \"startTime\" : \"16:58\",\n  \"endDate\" : \"2025-05-04\",\n  \"endTime\" : \"16:58\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponName\" : \"20% 할인 쿠폰\",\n    \"couponId\" : 1\n  }\n}"
                },
                "admin-event-controller-test/fail-parsing-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-04-28\",\n  \"startTime\" : \"16:58\",\n  \"endDate\" : \"2025-05-04\",\n  \"endTime\" : \"16:58\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponName\" : \"20% 할인 쿠폰\",\n    \"couponId\" : 1\n  }\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event/invalid-banner-id-update-event" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"배너가 존재하지 않습니다.\"}"
                  },
                  "admin-event-controller-test/invalid-banner-id-update-event" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"배너가 존재하지 않습니다.\"\n}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event/success-update-event" : {
                    "value" : "{\"message\":\"이벤트 수정이 완료되었습니다.\",\"data\":{\"id\":1,\"name\":\"상품 증정 선착순 이벤트\",\"description\":\"선착순 10명에게 소정의 상품을 드립니다\",\"startTime\":\"2025-03-08T18:00\",\"endTime\":\"2025-03-09T19:00\",\"eventDetail\":{\"couponName\":\"20% 할인 쿠폰\",\"couponId\":1}}}"
                  },
                  "admin-event-controller-test/success-update-event" : {
                    "value" : "{\n  \"message\" : \"이벤트 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"상품 증정 선착순 이벤트\",\n    \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n    \"startTime\" : \"2025-03-08T18:00\",\n    \"endTime\" : \"2025-03-09T19:00\",\n    \"eventDetail\" : {\n      \"couponName\" : \"20% 할인 쿠폰\",\n      \"couponId\" : 1\n    }\n  }\n}"
                  }
                }
              }
            }
          },
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event/missing-details-update-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"쿠폰 정보가 누락되었습니다.\"}"
                  },
                  "event/fail-parsing-json-node-update-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"JSON 데이터 검증 중 오류가 발생했습니다.\"}"
                  },
                  "event/fail-parsing-update-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"JSON 데이터 검증 중 오류가 발생했습니다.\"}"
                  },
                  "admin-event-controller-test/missing-details-update-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"쿠폰 정보가 누락되었습니다.\"\n}"
                  },
                  "admin-event-controller-test/fail-parsing-json-node-update-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"JSON 데이터 검증 중 오류가 발생했습니다.\"\n}"
                  },
                  "admin-event-controller-test/fail-parsing-update-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"JSON 데이터 검증 중 오류가 발생했습니다.\"\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/carts/items/{itemId}" : {
      "delete" : {
        "tags" : [ "Cart API", "api" ],
        "summary" : "장바구니 항목 제거 성공",
        "description" : "장바구니 항목 제거 성공",
        "operationId" : "cart",
        "parameters" : [ {
          "name" : "itemId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "204" : {
            "description" : "204",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cart/removeItem" : {
                    "value" : "{\n  \"message\" : \"장바구니에서 상품 제거가 완료되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "cart-controller-test/success-remove-item" : {
                    "value" : "{\n  \"message\" : \"장바구니에서 상품 제거가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "Cart API", "api" ],
        "summary" : "장바구니 항목 수량 변경 성공",
        "description" : "장바구니 항목 수량 변경 성공",
        "operationId" : "cart",
        "parameters" : [ {
          "name" : "itemId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "cart/updateItemQuantity" : {
                  "value" : "{\n  \"quantity\" : 2\n}"
                },
                "cart-controller-test/success-update-item-quantity" : {
                  "value" : "{\n  \"quantity\" : 2\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "cart/updateItemQuantity" : {
                    "value" : "{\n  \"message\" : \"장바구니에서 상품 수량 변경이 완료되었습니다.\",\n  \"data\" : {\n    \"cartItemId\" : 1,\n    \"product\" : {\n      \"productId\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"독한 술입니다.\",\n      \"price\" : 120000,\n      \"stockQuantity\" : 50\n    },\n    \"quantity\" : 3\n  }\n}"
                  },
                  "cart-controller-test/success-update-item-quantity" : {
                    "value" : "{\n  \"message\" : \"장바구니에서 상품 수량 변경이 완료되었습니다.\",\n  \"data\" : {\n    \"cartItemId\" : 1,\n    \"product\" : {\n      \"productId\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"독한 술입니다.\",\n      \"price\" : 120000,\n      \"stockQuantity\" : 50\n    },\n    \"quantity\" : 3\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/cocktails/{cocktailId}/favorites" : {
      "get" : {
        "tags" : [ "api", "Favorite API" ],
        "summary" : "즐겨찾기 여부 조회 API",
        "description" : "즐겨찾기 여부 조회 API",
        "operationId" : "favorite",
        "parameters" : [ {
          "name" : "cocktailId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-check-favorite" : {
                    "value" : "{\n  \"message\" : \"즐겨찾기 상태 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"favorited\" : true\n  }\n}"
                  },
                  "favorite/success-check-favorite" : {
                    "value" : "{\"message\":\"즐겨찾기 상태 조회가 완료되었습니다.\",\"data\":{\"favorited\":true}}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "api", "Favorite API" ],
        "summary" : "즐겨찾기 등록 API",
        "description" : "즐겨찾기 등록 API",
        "operationId" : "favorite",
        "parameters" : [ {
          "name" : "cocktailId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "409" : {
            "description" : "409",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "favorite-controller-test/duplicated-favorite-create-favorite" : {
                    "value" : "{\n  \"httpStatus\" : \"CONFLICT\",\n  \"errorMessage\" : \"이미 즐겨찾기한 레시피입니다.\"\n}"
                  },
                  "favorite/duplicated-favorite-create-favorite" : {
                    "value" : "{\"httpStatus\":\"CONFLICT\",\"errorMessage\":\"이미 즐겨찾기한 레시피입니다.\"}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "favorite-controller-test/invalid-favorite-id-create-favorite" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"칵테일이 존재하지 않습니다.\"\n}"
                  },
                  "favorite/invalid-favorite-id-create-favorite" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"칵테일이 존재하지 않습니다.\"}"
                  }
                }
              }
            }
          },
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-create-favorite" : {
                    "value" : "{\"message\":\"즐겨찾기 등록이 완료되었습니다.\",\"data\":{\"cocktailId\":1,\"name\":\"블랙 러시안\",\"description\":\"보드카와 깔루아로 만드는 칵테일\",\"type\":\"OFFICIAL\",\"favoritedAt\":\"2025-03-10T01:01:00\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/coupons/users/{userCouponId}" : {
      "get" : {
        "tags" : [ "api", "Coupon API" ],
        "summary" : "유저쿠폰 단건 조회 API",
        "description" : "로그인한 사용자의 특정 쿠폰 정보를 조회합니다.\n\n## 사용 예시\n사용자 쿠폰 ID를 지정하여 쿠폰 정보 조회\n\n## example\n1. 200 OK\n- 유저쿠폰 단건 조회 성공: 유저쿠폰 아이디로 로그인한 사용자 본인의 쿠폰 단건 조회\n2. 404 Not Found\n- 유저쿠폰 단건 조회 실패: 존재하지 않는 유저쿠폰 아이디로 조회 시도 - 존재하지 않는 유저쿠폰 아이디로 조회시도 시 예외발생",
        "operationId" : "coupon",
        "parameters" : [ {
          "name" : "userCouponId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-user-coupon" : {
                    "value" : "{\n  \"message\" : \"쿠폰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"userCouponId\" : 1,\n    \"userId\" : 1,\n    \"coupon\" : {\n      \"id\" : 1,\n      \"couponName\" : \"1000원 할인쿠폰\",\n      \"discountValue\" : 1000\n    },\n    \"status\" : \"ISSUED\",\n    \"usedTime\" : null\n  }\n}"
                  },
                  "coupon/success-get-user-coupon" : {
                    "value" : "{\"message\":\"쿠폰 조회가 완료되었습니다.\",\"data\":{\"userCouponId\":1,\"userId\":1,\"coupon\":{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000},\"status\":\"ISSUED\",\"usedTime\":null}}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "coupon-controller-test/invalid-user-coupon-id-get-user-coupon" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"쿠폰이 존재하지 않습니다\"}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "Coupon API" ],
        "summary" : "유저쿠폰 사용 API",
        "description" : "로그인한 사용자가 발급된 유저쿠폰을 사용합니다.\n\n## 사용 예시\n사용자 쿠폰 ID를 지정하여 쿠폰 사용\n\n## example\n1. 201 Created\n- 유저쿠폰 사용 성공: 로그인한 사용자가 발급된 유저쿠폰 사용\n2. 404 Not Found\n- 유저쿠폰 사용 실패: 존재하지 않는 유저쿠폰 아이디 or 사용 혹은 만료된 쿠폰 - 로그인한 존재하지 않는 유저쿠폰 아이디 혹은 사용되거나 만료된 쿠폰 사용 시도 시 예외발생",
        "operationId" : "coupon-controller-test/",
        "parameters" : [ {
          "name" : "userCouponId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-use-user-coupon" : {
                    "value" : "{\"message\":\"쿠폰 사용이 완료되었습니다.\",\"data\":{\"userCouponId\":1,\"userId\":1,\"coupon\":{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000},\"status\":\"ISSUED\",\"usedTime\":null}}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "coupon-controller-test/invalid-user-coupon-id-use-user-coupon" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"쿠폰이 존재하지 않습니다\"}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/deliveries/orders/{orderId}" : {
      "get" : {
        "tags" : [ "Delivery API", "api" ],
        "summary" : "USER 배송정보 단건 조회 API",
        "description" : "로그인한 사용자가 자신의 주문에 대한 배송 정보를 조회합니다.\n\n## 사용 예시\n주문 ID로 배송 정보 조회\n\n## example\n1. 200 OK\n- USER 배송정보 단건 조회 성공: 로그인한 사용자가 본인의 주문내역에 대한 배송정보 조회\n2. 404 Not Found\n- USER 배송정보 조회 실패: 존재하지 않는 주문 아이디로 조회 - 로그인한 사용자가 존재하지 않는 주문 아이디로 배송정보 조회 시 예외 발생\n3. 403 Forbidden\n- USER 유저의 배송정보 조회 실패: 타인의 주문 아이디로 배송정보를 조회 - 로그인한 사용자가 타인의 주문 아이디로 배송정보를 조회 시 예외 발생",
        "operationId" : "delivery",
        "parameters" : [ {
          "name" : "orderId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "delivery/invalid-order-id-get-shipping-for-user" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"배송 정보가 존재하지 않습니다.\"}"
                  },
                  "delivery-controller-test/invalid-order-id-get-shipping-for-user" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"배송 정보가 존재하지 않습니다.\"\n}"
                  }
                }
              }
            }
          },
          "403" : {
            "description" : "403",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "delivery/forbidden-order-id-get-shipping-for-user" : {
                    "value" : "{\"httpStatus\":\"FORBIDDEN\",\"errorMessage\":\"접근 권한이 없습니다.\"}"
                  },
                  "delivery-controller-test/forbidden-order-id-get-shipping-for-user" : {
                    "value" : "{\n  \"httpStatus\" : \"FORBIDDEN\",\n  \"errorMessage\" : \"접근 권한이 없습니다.\"\n}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "delivery-controller-test/success-get-shipping-for-user" : {
                    "value" : "{\"message\":\"배송 정보 조회가 완료되었습니다.\",\"data\":{\"deliveryId\":2,\"orderId\":1,\"courierName\":\"대한통운\",\"trackingNumber\":\"123456789\",\"status\":\"REGISTERED\",\"deliveryMessage\":\"현관 비밀번호는 #0000#입니다.\"}}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/events/{eventId}/participation" : {
      "post" : {
        "tags" : [ "api", "Event API" ],
        "summary" : "이벤트 참여 API",
        "description" : "사용자가 이벤트에 참여합니다.\n\n## 사용 예시\n이벤트 ID를 지정하여 이벤트 참여\n\n## example\n1. 200 OK\n- 이벤트 참여 성공: 사용자가 이벤트 참여\n2. 400 Bad Request\n- 이벤트 참여 실패: 이벤트 상태가 'ONGOING'이 아닌 경우 - 이벤트 참여 시도 시, 이벤트 상태가 ONGOING이 아닌 경우 예외 발생\n- 이벤트 참여 실패: 쿠폰 정보 누락 - 이벤트 참여 시도 시 쿠폰 정보가 누락된 경우 예외 발생(이벤트 생성, 수정시 체크하고 있지만 혹시모르는 상황을 고려하여 구현)",
        "operationId" : "event",
        "parameters" : [ {
          "name" : "eventId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event-controller-test/success-participate-event" : {
                    "value" : "{\n  \"message\" : \"이벤트 참여를 완료했습니다.\",\n  \"data\" : {\n    \"eventId\" : 1,\n    \"userId\" : 1,\n    \"couponId\" : 1,\n    \"eventResult\" : \"WINNER\"\n  }\n}"
                  },
                  "event/success-participate-event" : {
                    "value" : "{\"message\":\"이벤트 참여를 완료했습니다.\",\"data\":{\"eventId\":1,\"userId\":1,\"couponId\":1,\"eventResult\":\"WINNER\"}}"
                  }
                }
              }
            }
          },
          "400" : {
            "description" : "400",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event-controller-test/invalid-status-participate-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"유효하지 않은 이벤트 상태입니다.\"\n}"
                  },
                  "event-controller-test/missing-details-participate-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"쿠폰 정보가 누락되었습니다.\"\n}"
                  },
                  "event/invalid-status-participate-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"유효하지 않은 이벤트 상태입니다.\"}"
                  },
                  "event/missing-details-participate-event" : {
                    "value" : "{\"httpStatus\":\"BAD_REQUEST\",\"errorMessage\":\"쿠폰 정보가 누락되었습니다.\"}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/ingredients/reviews/me" : {
      "get" : {
        "tags" : [ "IngredientReview API", "api" ],
        "summary" : "내 재료 리뷰 조회",
        "description" : "내 재료 리뷰 조회",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "기본값 : 1",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "기본값 : 10",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredientReview/myIngredientReview" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"ingredient\" : {\n        \"ingredientId\" : 1,\n        \"name\" : \"봄베이 사파이어\",\n        \"avb\" : 47.0,\n        \"description\" : \"주류계의 민트초코\",\n        \"category\" : \"JIN\"\n      },\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickName\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"reviewId\" : 1,\n      \"star\" : 1,\n      \"content\" : \"최악의 재료\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  },
                  "ingredient-review-controller-test/success-get-my-ingredient-review" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"ingredient\" : {\n        \"ingredientId\" : 1,\n        \"name\" : \"봄베이 사파이어\",\n        \"avb\" : 47.0,\n        \"description\" : \"주류계의 민트초코\",\n        \"category\" : \"JIN\"\n      },\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickName\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"reviewId\" : 1,\n      \"star\" : 1,\n      \"content\" : \"최악의 재료\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/ingredients/reviews/{reviewId}" : {
      "delete" : {
        "tags" : [ "IngredientReview API", "api" ],
        "summary" : "재료 리뷰 제거",
        "description" : "재료 리뷰 제거",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "reviewId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredientReview/deleteIngredientReview" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  },
                  "ingredient-review-controller-test/success-delete-ingredient-review" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "IngredientReview API", "api" ],
        "summary" : "재료 리뷰 수정",
        "description" : "재료 리뷰 수정",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "reviewId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "ingredientReview/updateIngredientReview" : {
                  "value" : "{\n  \"star\" : 1,\n  \"content\" : \"최악의 재료\"\n}"
                },
                "ingredient-review-controller-test/success-update-ingredient-review" : {
                  "value" : "{\n  \"star\" : 1,\n  \"content\" : \"최악의 재료\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredientReview/updateIngredientReview" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"ingredient\" : {\n      \"ingredientId\" : 1,\n      \"name\" : \"봄베이 사파이어\",\n      \"avb\" : 47.0,\n      \"description\" : \"주류계의 민트초코\",\n      \"category\" : \"JIN\"\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 1,\n    \"content\" : \"최악의 재료\"\n  }\n}"
                  },
                  "ingredient-review-controller-test/success-update-ingredient-review" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"ingredient\" : {\n      \"ingredientId\" : 1,\n      \"name\" : \"봄베이 사파이어\",\n      \"avb\" : 47.0,\n      \"description\" : \"주류계의 민트초코\",\n      \"category\" : \"JIN\"\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 1,\n    \"content\" : \"최악의 재료\"\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/ingredients/reviews/{reviewsId}" : {
      "get" : {
        "tags" : [ "IngredientReview API", "api" ],
        "summary" : "재료 리뷰 단건 조회",
        "description" : "재료 리뷰 단건 조회",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "reviewsId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredientReview/getIngredientReview" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"ingredient\" : {\n      \"ingredientId\" : 1,\n      \"name\" : \"봄베이 사파이어\",\n      \"avb\" : 47.0,\n      \"description\" : \"주류계의 민트초코\",\n      \"category\" : \"JIN\"\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 1,\n    \"content\" : \"최악의 재료\"\n  }\n}"
                  },
                  "ingredient-review-controller-test/success-get-recipe-review" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"ingredient\" : {\n      \"ingredientId\" : 1,\n      \"name\" : \"봄베이 사파이어\",\n      \"avb\" : 47.0,\n      \"description\" : \"주류계의 민트초코\",\n      \"category\" : \"JIN\"\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 1,\n    \"content\" : \"최악의 재료\"\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/ingredients/{ingredientId}/reviews" : {
      "get" : {
        "tags" : [ "IngredientReview API", "api" ],
        "summary" : "재료 리뷰 전체 조회",
        "description" : "재료 리뷰 전체 조회",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "ingredientId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "page",
          "in" : "query",
          "description" : "기본값 : 1",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "기본값 : 10",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredientReview/getAllIngredientReview" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"ingredient\" : {\n        \"ingredientId\" : 1,\n        \"name\" : \"봄베이 사파이어\",\n        \"avb\" : 47.0,\n        \"description\" : \"주류계의 민트초코\",\n        \"category\" : \"JIN\"\n      },\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickName\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"reviewId\" : 1,\n      \"star\" : 1,\n      \"content\" : \"최악의 재료\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  },
                  "ingredient-review-controller-test/success-get-all-ingredient-review" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"ingredient\" : {\n        \"ingredientId\" : 1,\n        \"name\" : \"봄베이 사파이어\",\n        \"avb\" : 47.0,\n        \"description\" : \"주류계의 민트초코\",\n        \"category\" : \"JIN\"\n      },\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickName\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"reviewId\" : 1,\n      \"star\" : 1,\n      \"content\" : \"최악의 재료\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "IngredientReview API", "api" ],
        "summary" : "재료 리뷰 생성",
        "description" : "재료 리뷰 생성",
        "operationId" : "ingredient",
        "parameters" : [ {
          "name" : "ingredientId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "ingredientReview/createIngredient" : {
                  "value" : "{\n  \"star\" : 1,\n  \"content\" : \"최악의 재료\"\n}"
                },
                "ingredient-review-controller-test/success-create-ingredient-review" : {
                  "value" : "{\n  \"star\" : 1,\n  \"content\" : \"최악의 재료\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "ingredientReview/createIngredient" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"ingredient\" : {\n      \"ingredientId\" : 1,\n      \"name\" : \"봄베이 사파이어\",\n      \"avb\" : 47.0,\n      \"description\" : \"주류계의 민트초코\",\n      \"category\" : \"JIN\"\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 1,\n    \"content\" : \"최악의 재료\"\n  }\n}"
                  },
                  "ingredient-review-controller-test/success-create-ingredient-review" : {
                    "value" : "{\n  \"message\" : \"재료 리뷰 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"ingredient\" : {\n      \"ingredientId\" : 1,\n      \"name\" : \"봄베이 사파이어\",\n      \"avb\" : 47.0,\n      \"description\" : \"주류계의 민트초코\",\n      \"category\" : \"JIN\"\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 1,\n    \"content\" : \"최악의 재료\"\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/pantries/ingredients/{ingredientId}" : {
      "post" : {
        "tags" : [ "Pantry_API", "api" ],
        "summary" : "펜트리 재료 추가 실패",
        "description" : "펜트리 재료 추가 실패 - 이미 추가된 재료",
        "operationId" : "pantry",
        "parameters" : [ {
          "name" : "ingredientId",
          "in" : "path",
          "description" : "추가할 재료의 ID",
          "required" : true,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        } ],
        "responses" : {
          "409" : {
            "description" : "409",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "pantry/addIngrToPantry_failedCase2" : {
                    "value" : "{\n  \"httpStatus\" : \"CONFLICT\",\n  \"errorMessage\" : \"이미 등록된 재료입니다.\"\n}"
                  },
                  "pantry-controller-test/재료추가_실패_재료_중복_추가_시도" : {
                    "value" : "{\n  \"httpStatus\" : \"CONFLICT\",\n  \"errorMessage\" : \"이미 등록된 재료입니다.\"\n}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "pantry/addIngrToPantry" : {
                    "value" : "{\n  \"message\" : \"팬트리 재료 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"userId\" : 1,\n    \"pantryId\" : 1,\n    \"ingredient\" : {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }\n  }\n}"
                  },
                  "pantry-controller-test/펜트리_재료추가_성공" : {
                    "value" : "{\n  \"message\" : \"팬트리 재료 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"userId\" : 1,\n    \"pantryId\" : 1,\n    \"ingredient\" : {\n      \"id\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"보드카\",\n      \"category\" : \"VODKA\",\n      \"imageUrl\" : \"대충 이미지 주소\",\n      \"avb\" : 40.0\n    }\n  }\n}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "pantry/addIngrToPantry_failedCase1" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"재료가 존재하지 않습니다.\"\n}"
                  },
                  "pantry-controller-test/재료추가_실패_재료_미존재" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"재료가 존재하지 않습니다.\"\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/recipes/reviews/me" : {
      "get" : {
        "tags" : [ "RecipeReview API", "api" ],
        "summary" : "내 레시피 리뷰 조회",
        "description" : "내 레시피 리뷰 조회",
        "operationId" : "recipe",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "기본값 : 1",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "기본값 : 10",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "recipeReview/getMyRecipeReview" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"cocktail\" : {\n        \"id\" : 1,\n        \"name\" : \"블랙 러시안\",\n        \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n        \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n        \"type\" : \"CUSTOM\",\n        \"likeCounts\" : 100,\n        \"userId\" : 1\n      },\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickName\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"reviewId\" : 1,\n      \"star\" : 5,\n      \"content\" : \"맛있어요!\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  },
                  "recipe-review-controller-test/success-get-my-recipe-review" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"cocktail\" : {\n        \"id\" : 1,\n        \"name\" : \"블랙 러시안\",\n        \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n        \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n        \"type\" : \"CUSTOM\",\n        \"likeCounts\" : 100,\n        \"userId\" : 1\n      },\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickName\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"reviewId\" : 1,\n      \"star\" : 5,\n      \"content\" : \"맛있어요!\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/recipes/reviews/{reviewId}" : {
      "get" : {
        "tags" : [ "RecipeReview API", "api" ],
        "summary" : "레시피 리뷰 단건 조회",
        "description" : "레시피 리뷰 단건 조회",
        "operationId" : "recipe",
        "parameters" : [ {
          "name" : "reviewId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "recipeReview/getRecipeReview" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"cocktail\" : {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"CUSTOM\",\n      \"likeCounts\" : 100,\n      \"userId\" : 1\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 5,\n    \"content\" : \"맛있어요!\"\n  }\n}"
                  },
                  "recipe-review-controller-test/success-get-recipe-review" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"cocktail\" : {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"CUSTOM\",\n      \"likeCounts\" : 100,\n      \"userId\" : 1\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 5,\n    \"content\" : \"맛있어요!\"\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "api" ],
        "operationId" : "recipe-review-controller-test/success-delete-recipe-review",
        "parameters" : [ {
          "name" : "reviewId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "recipe-review-controller-test/success-delete-recipe-review" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "RecipeReview API", "api" ],
        "summary" : "레시피 리뷰 수정",
        "description" : "레시피 리뷰 수정",
        "operationId" : "recipe",
        "parameters" : [ {
          "name" : "reviewId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "recipeReview/updateRecipeReview" : {
                  "value" : "{\n  \"star\" : 5,\n  \"content\" : \"맛있어요!\"\n}"
                },
                "recipe-review-controller-test/success-update-recipe-review" : {
                  "value" : "{\n  \"star\" : 5,\n  \"content\" : \"맛있어요!\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "recipeReview/updateRecipeReview" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"cocktail\" : {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"CUSTOM\",\n      \"likeCounts\" : 100,\n      \"userId\" : 1\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 5,\n    \"content\" : \"맛있어요!\"\n  }\n}"
                  },
                  "recipe-review-controller-test/success-update-recipe-review" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"cocktail\" : {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"CUSTOM\",\n      \"likeCounts\" : 100,\n      \"userId\" : 1\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 5,\n    \"content\" : \"맛있어요!\"\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/recipes/{cocktailId}/reviews" : {
      "get" : {
        "tags" : [ "RecipeReview API", "api" ],
        "summary" : "레시피 리뷰 전체 조회",
        "description" : "레시피 리뷰 전체 조회",
        "operationId" : "recipe",
        "parameters" : [ {
          "name" : "cocktailId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "page",
          "in" : "query",
          "description" : "기본값 : 1",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "기본값 : 10",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int32"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "recipeReview/getAllRecipeReview" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"cocktail\" : {\n        \"id\" : 1,\n        \"name\" : \"블랙 러시안\",\n        \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n        \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n        \"type\" : \"CUSTOM\",\n        \"likeCounts\" : 100,\n        \"userId\" : 1\n      },\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickName\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"reviewId\" : 1,\n      \"star\" : 5,\n      \"content\" : \"맛있어요!\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  },
                  "recipe-review-controller-test/success-get-all-recipe-review" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"cocktail\" : {\n        \"id\" : 1,\n        \"name\" : \"블랙 러시안\",\n        \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n        \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n        \"type\" : \"CUSTOM\",\n        \"likeCounts\" : 100,\n        \"userId\" : 1\n      },\n      \"user\" : {\n        \"id\" : 1,\n        \"email\" : \"test@mail.com\",\n        \"nickName\" : \"nickname\",\n        \"userRole\" : \"USER\"\n      },\n      \"reviewId\" : 1,\n      \"star\" : 5,\n      \"content\" : \"맛있어요!\"\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "RecipeReview API", "api" ],
        "summary" : "레시피 리뷰 생성",
        "description" : "레시피 리뷰 생성",
        "operationId" : "recipe",
        "parameters" : [ {
          "name" : "cocktailId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "recipeReview/createRecipeReview" : {
                  "value" : "{\n  \"star\" : 5,\n  \"content\" : \"맛있어요!\"\n}"
                },
                "recipe-review-controller-test/success-create-recipe-review" : {
                  "value" : "{\n  \"star\" : 5,\n  \"content\" : \"맛있어요!\"\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "recipeReview/createRecipeReview" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"cocktail\" : {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"CUSTOM\",\n      \"likeCounts\" : 100,\n      \"userId\" : 1\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 5,\n    \"content\" : \"맛있어요!\"\n  }\n}"
                  },
                  "recipe-review-controller-test/success-create-recipe-review" : {
                    "value" : "{\n  \"message\" : \"레시피 리뷰 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"cocktail\" : {\n      \"id\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\n      \"type\" : \"CUSTOM\",\n      \"likeCounts\" : 100,\n      \"userId\" : 1\n    },\n    \"user\" : {\n      \"id\" : 1,\n      \"email\" : \"test@mail.com\",\n      \"nickName\" : \"nickname\",\n      \"userRole\" : \"USER\"\n    },\n    \"reviewId\" : 1,\n    \"star\" : 5,\n    \"content\" : \"맛있어요!\"\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/coupons/{couponId}/users" : {
      "post" : {
        "tags" : [ "Coupon API" ],
        "summary" : "ADMIN 유저쿠폰 발급 API",
        "description" : "관리자가 사용자에게 쿠폰을 발급합니다.\n\n## 사용 예시\n쿠폰 ID와 사용자 ID를 지정하여 유저쿠폰 발급\n\n## example\n1. 201 Created\n- ADMIN 유저쿠폰 발급 성공: 관리자가 유저가 사용할 유저쿠폰 발급\n2. 404 Not Found\n- 유저쿠폰 발급 실패: 존재하지 않는 쿠폰아이디로 유저쿠폰 발급 시도 - 관리자가 존재하지 않는 쿠폰아이디로 유저쿠폰 발급 시도 시 예외발생",
        "operationId" : "admin-coupon-controller-test/",
        "parameters" : [ {
          "name" : "couponId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "admin-coupon-controller-test/success-grant-user-coupon" : {
                  "value" : "{\"userId\":1}"
                },
                "admin-coupon-controller-test/invalid-coupon-id-grant-user-coupon" : {
                  "value" : "{\"userId\":1}"
                }
              }
            }
          }
        },
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-grant-user-coupon" : {
                    "value" : "{\"message\":\"쿠폰 지급이 완료되었습니다.\",\"data\":{\"userCouponId\":1,\"userId\":1,\"coupon\":{\"id\":1,\"couponName\":\"1000원 할인쿠폰\",\"discountValue\":1000},\"status\":\"ISSUED\"}}"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/invalid-coupon-id-grant-user-coupon" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"쿠폰이 존재하지 않습니다\"}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/events/event-stream/{eventId}" : {
      "get" : {
        "tags" : [ "Event API", "api" ],
        "summary" : "이벤트 결과 스트림 구독 API",
        "description" : "이벤트 진행 상황을 실시간으로 확인할 수 있는 스트림을 제공합니다.\n\n## 사용 예시\n이벤트 ID를 지정하여 SSE 스트림 구독\n\n## example\n1. 200 OK\n- 이벤트 결과 스트림 구독 및 Emitter 반환 성공: 사용자가 이벤트 참여를 위해 이벤트 정보 화면에 진입 시, SSE 스트림을 구독하고 해당 연결의 Emitter를 반환\n2. 404 Not Found\n- 이벤트 결과 스트림 구독 및 Emitter 반환 성공: 스트림 구독을 위해 이벤트 상태 조회 시 존재하지 않는 이벤트아이디가 입력된 경우 예외 발생",
        "operationId" : "admin-event-controller-test/invalid-event-id-stream-event-updatesadmin-event-controller-test/success-stream-event-updatesevent/invalid-event-id-stream-event-updatesevent/success-stream-event-updates",
        "parameters" : [ {
          "name" : "eventId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "event/invalid-event-id-stream-event-updates" : {
                    "value" : "{\"httpStatus\":\"NOT_FOUND\",\"errorMessage\":\"이벤트가 존재하지 않습니다.\"}"
                  },
                  "admin-event-controller-test/invalid-event-id-stream-event-updates" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"이벤트가 존재하지 않습니다.\"\n}"
                  }
                }
              }
            }
          },
          "200" : {
            "description" : "200"
          }
        }
      }
    },
    "/api/super-admin/users/{userId}/role" : {
      "patch" : {
        "tags" : [ "User API" ],
        "summary" : "SUPER_ADMIN 유저 권한 변경 API",
        "description" : "수퍼 관리자가 다른 사용자의 권한을 변경합니다.\n\n## 사용 예시\n사용자 ID와 변경할 권한 정보를 지정\n\n## example\n1. 200 OK\n- SUPER_ADMIN 유저 권한 변경 성공: 수퍼어드민 계정으로 유저(USER)와 관리자(ADMIN) 권한 변경\n2. 409 Conflict\n- SUPER_ADMIN 유저 권한 변경 실패: 같은 권한으로 변경 시도 - 수퍼어드민 권한으로 권한변경 시 같은 권한으로 변경시도할 경우 예외 발생",
        "operationId" : "super-admin-user-controller-test/",
        "parameters" : [ {
          "name" : "userId",
          "in" : "path",
          "description" : "",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
              },
              "examples" : {
                "super-admin-user-controller-test/success-update-user-role" : {
                  "value" : "{\"userRole\":\"ADMIN\"}"
                },
                "super-admin-user-controller-test/duplicated-user-role-update-user-role" : {
                  "value" : "{\"userRole\":\"ADMIN\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "super-admin-user-controller-test/success-update-user-role" : {
                    "value" : "{\"message\":\"권한 변경이 완료되었습니다.\",\"data\":{\"userId\":1,\"userRole\":\"ADMIN\"}}"
                  }
                }
              }
            }
          },
          "409" : {
            "description" : "409",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users-userCouponId486549215"
                },
                "examples" : {
                  "super-admin-user-controller-test/duplicated-user-role-update-user-role" : {
                    "value" : "{\"httpStatus\":\"CONFLICT\",\"errorMessage\":\"동일한 권한입니다.\"}"
                  }
                }
              }
            }
          }
        }
      }
    }
  },
  "components" : {
    "schemas" : {
      "api-coupons-users-userCouponId486549215" : {
        "type" : "object"
      }
    },
    "securitySchemes" : {
      "APIKey" : {
        "type" : "apiKey",
        "name" : "Authorization",
        "in" : "header"
      }
    }
  },
  "security" : [ {
    "APIKey" : [ ]
  } ]
}