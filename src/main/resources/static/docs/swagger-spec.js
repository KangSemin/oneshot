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
        "tags" : [ "api" ],
        "operationId" : "address-controller-test/success-get-addresses",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/success-get-addresses-first-page" : {
                    "value" : "{\n  \"message\" : \"주소지 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"addresses\" : [ {\n      \"addressId\" : 1,\n      \"addressName\" : \"집\",\n      \"postAddress\" : \"12345\",\n      \"detailAddress\" : \"서울시 강서구\",\n      \"default\" : false\n    } ],\n    \"hasNext\" : false,\n    \"nextCursor\" : 1\n  }\n}"
                  },
                  "address-controller-test/success-get-addresses" : {
                    "value" : "{\n  \"message\" : \"주소지 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"addresses\" : [ {\n      \"addressId\" : 1,\n      \"addressName\" : \"집\",\n      \"postAddress\" : \"12345\",\n      \"detailAddress\" : \"서울시 강서구\",\n      \"default\" : false\n    }, {\n      \"addressId\" : 1,\n      \"addressName\" : \"집\",\n      \"postAddress\" : \"12345\",\n      \"detailAddress\" : \"서울시 강서구\",\n      \"default\" : false\n    } ],\n    \"hasNext\" : true,\n    \"nextCursor\" : 2\n  }\n}"
                  },
                  "address-controller-test/success-get-addresses-empty-list" : {
                    "value" : "{\n  \"message\" : \"주소지 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"addresses\" : [ ],\n    \"hasNext\" : false,\n    \"nextCursor\" : null\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "api" ],
        "operationId" : "address-controller-test/success-create-address",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "address-controller-test/success-create-address" : {
                  "value" : "{\n  \"addressName\" : \"집\",\n  \"postcode\" : \"12345\",\n  \"postAddress\" : \"서울시 강서구\",\n  \"detailAddress\" : \"아파트 101동 202호\",\n  \"extraAddress\" : \"배송전 연락주세요.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/success-create-address" : {
                    "value" : "{\n  \"message\" : \"주소 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"addressId\" : 1,\n    \"addressName\" : \"집\",\n    \"postcode\" : \"12345\",\n    \"postAddress\" : \"서울시 강서구\",\n    \"detailAddress\" : \"아파트 101동 202호\",\n    \"extraAddress\" : \"배송전 연락주세요.\",\n    \"default\" : false\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "banner-controller-test/success-get-banners",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "banner-controller-test/success-get-banners" : {
                    "value" : "{\n  \"message\" : \"배너 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"banners\" : [ {\n      \"bannerId\" : 1,\n      \"eventId\" : 1,\n      \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n      \"startTime\" : \"2025-03-09T18:00\",\n      \"endTime\" : \"2025-03-11T00:00\"\n    }, {\n      \"bannerId\" : 2,\n      \"eventId\" : 2,\n      \"imageUrl\" : \"https://via.placeholder.com/1200x300/33FF57/000000?text=Limited+Time+Offer\",\n      \"startTime\" : \"2025-03-10T18:00\",\n      \"endTime\" : \"2025-03-12T18:00\"\n    }, {\n      \"bannerId\" : 3,\n      \"eventId\" : 3,\n      \"imageUrl\" : \"https://via.placeholder.com/1200x300/33A8FF/FFFFFF?text=Special+Promotion\",\n      \"startTime\" : \"2025-03-11T18:00\",\n      \"endTime\" : \"2025-03-12T18:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "banner-controller-test/success-get-banners-with-start-and-end-date" : {
                    "value" : "{\n  \"message\" : \"배너 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"banners\" : [ {\n      \"bannerId\" : 3,\n      \"eventId\" : 3,\n      \"imageUrl\" : \"https://via.placeholder.com/1200x300/33A8FF/FFFFFF?text=Special+Promotion\",\n      \"startTime\" : \"2025-03-11T18:00\",\n      \"endTime\" : \"2025-03-12T18:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "banner-controller-test/success-get-banners-with-empty" : {
                    "value" : "{\n  \"message\" : \"배너 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"banners\" : [ ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "banner-controller-test/success-get-banners-with-end-date" : {
                    "value" : "{\n  \"message\" : \"배너 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"banners\" : [ {\n      \"bannerId\" : 1,\n      \"eventId\" : 1,\n      \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n      \"startTime\" : \"2025-03-09T18:00\",\n      \"endTime\" : \"2025-03-11T00:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "banner-controller-test/success-get-banners-with-start-date" : {
                    "value" : "{\n  \"message\" : \"배너 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"banners\" : [ {\n      \"bannerId\" : 2,\n      \"eventId\" : 2,\n      \"imageUrl\" : \"https://via.placeholder.com/1200x300/33FF57/000000?text=Limited+Time+Offer\",\n      \"startTime\" : \"2025-03-10T18:00\",\n      \"endTime\" : \"2025-03-12T18:00\"\n    }, {\n      \"bannerId\" : 3,\n      \"eventId\" : 3,\n      \"imageUrl\" : \"https://via.placeholder.com/1200x300/33A8FF/FFFFFF?text=Special+Promotion\",\n      \"startTime\" : \"2025-03-11T18:00\",\n      \"endTime\" : \"2025-03-12T18:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "cart-controller-test/success-find-cart",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "cart-controller-test/success-empty-cart",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "chat-controller-test/success-find-chat",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "cocktail-controller-test/find-cocktails-by-keyword",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
      },
      "post" : {
        "tags" : [ "api", "Cocktail API" ],
        "summary" : "칵테일 생성",
        "description" : "칵테일 생성",
        "operationId" : "cocktail",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
        "tags" : [ "api" ],
        "operationId" : "coupon-controller-test/success-get-",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-coupons-with-start-and-end-date" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"coupons\" : [ {\n      \"id\" : 2,\n      \"couponName\" : \"2000원 할인쿠폰\",\n      \"discountValue\" : 2000\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-coupons-for-event-with-event-start-and-end-date" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"coupons\" : [ {\n      \"id\" : 1,\n      \"couponName\" : \"1000원 할인쿠폰\",\n      \"discountValue\" : 1000\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-coupons-for-event" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"coupons\" : [ {\n      \"id\" : 1,\n      \"couponName\" : \"1000원 할인쿠폰\",\n      \"discountValue\" : 1000\n    }, {\n      \"id\" : 2,\n      \"couponName\" : \"2000원 할인쿠폰\",\n      \"discountValue\" : 2000\n    }, {\n      \"id\" : 3,\n      \"couponName\" : \"3000원 할인쿠폰\",\n      \"discountValue\" : 3000\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-coupons-for-event-with-event-start-date" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"coupons\" : [ {\n      \"id\" : 1,\n      \"couponName\" : \"1000원 할인쿠폰\",\n      \"discountValue\" : 1000\n    }, {\n      \"id\" : 3,\n      \"couponName\" : \"3000원 할인쿠폰\",\n      \"discountValue\" : 3000\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-empty-coupons" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"coupons\" : [ ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-coupons-with-start-date" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"coupons\" : [ {\n      \"id\" : 2,\n      \"couponName\" : \"2000원 할인쿠폰\",\n      \"discountValue\" : 2000\n    }, {\n      \"id\" : 3,\n      \"couponName\" : \"3000원 할인쿠폰\",\n      \"discountValue\" : 3000\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-coupons-for-event-with-event-end-date" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"coupons\" : [ {\n      \"id\" : 3,\n      \"couponName\" : \"3000원 할인쿠폰\",\n      \"discountValue\" : 3000\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-coupons-with-end-date" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"coupons\" : [ {\n      \"id\" : 1,\n      \"couponName\" : \"1000원 할인쿠폰\",\n      \"discountValue\" : 1000\n    }, {\n      \"id\" : 3,\n      \"couponName\" : \"3000원 할인쿠폰\",\n      \"discountValue\" : 3000\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-coupons" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"coupons\" : [ {\n      \"id\" : 1,\n      \"couponName\" : \"1000원 할인쿠폰\",\n      \"discountValue\" : 1000\n    }, {\n      \"id\" : 2,\n      \"couponName\" : \"2000원 할인쿠폰\",\n      \"discountValue\" : 2000\n    }, {\n      \"id\" : 3,\n      \"couponName\" : \"3000원 할인쿠폰\",\n      \"discountValue\" : 3000\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "event-controller-test/success-get-events",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                    "value" : "{\n  \"message\" : \"이벤트 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"events\" : [ {\n      \"id\" : 2,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-07T18:00\",\n      \"endTime\" : \"2025-03-09T18:00\"\n    }, {\n      \"id\" : 1,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-08T18:00\",\n      \"endTime\" : \"2025-03-09T19:00\"\n    }, {\n      \"id\" : 3,\n      \"name\" : \"상품 증정 선착순 이벤트\",\n      \"startTime\" : \"2025-03-09T18:00\",\n      \"endTime\" : \"2025-03-10T18:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "favorite-controller-test/success-get-",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-get-favorites" : {
                    "value" : "{\n  \"message\" : \"즐겨찾기 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"favorites\" : [ {\n      \"cocktailId\" : 1,\n      \"name\" : \"블랙 러시안\",\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n      \"type\" : \"OFFICIAL\",\n      \"favoritedAt\" : \"2025-03-10T01:01:00\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "favorite-controller-test/success-get-empty-favorites" : {
                    "value" : "{\n  \"message\" : \"즐겨찾기 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"favorites\" : [ ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "ingredient-controller-test/재료생성_",
        "responses" : {
          "201" : {
            "description" : "201",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료생성_성공" : {
                    "value" : "{\n  \"message\" : \"재료 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"보드카\",\n    \"category\" : \"VODKA\",\n    \"imageUrl\" : \"대충 이미지 주소\",\n    \"avb\" : 40.0\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료생성_실패_case_이미지업로드_실패" : {
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
        "tags" : [ "api" ],
        "operationId" : "order-controller-test/success-get-all-order",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "order-controller-test/success-create-order",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
    "/api/products" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "product-controller-test/success-get-all-product",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "product-controller-test/success-get-all-product" : {
                    "value" : "{\n  \"message\" : \"상품 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"content\" : [ {\n      \"productId\" : 1,\n      \"name\" : \"보드카\",\n      \"description\" : \"독한 술입니다.\",\n      \"price\" : 120000,\n      \"stockQuantity\" : 50\n    } ],\n    \"pageable\" : \"INSTANCE\",\n    \"last\" : true,\n    \"totalPages\" : 1,\n    \"totalElements\" : 1,\n    \"size\" : 1,\n    \"number\" : 0,\n    \"sort\" : {\n      \"empty\" : true,\n      \"sorted\" : false,\n      \"unsorted\" : true\n    },\n    \"first\" : true,\n    \"numberOfElements\" : 1,\n    \"empty\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "api" ],
        "operationId" : "product-controller-test/success-create-product",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "product-controller-test/success-create-product" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "product-controller-test/success-create-product" : {
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
        "tags" : [ "api" ],
        "operationId" : "user-controller-test/",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "user-controller-test/success-get-user-info" : {
                    "value" : "{\n  \"message\" : \"사용자 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"email\" : \"test@mail.com\",\n    \"nickname\" : \"nickname\",\n    \"userRole\" : \"USER\"\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "user-controller-test/invalid-user-id-get-user-info" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"유저가 존재하지 않습니다.\"\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "api" ],
        "operationId" : "user-controller-test/",
        "responses" : {
          "409" : {
            "description" : "409",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "user-controller-test/",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-address-id-get-address" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"해당 주소가 존재하지 않습니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/success-get-address" : {
                    "value" : "{\n  \"message\" : \"주소 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"addressId\" : 1,\n    \"addressName\" : \"집\",\n    \"postcode\" : \"12345\",\n    \"postAddress\" : \"서울시 강서구\",\n    \"detailAddress\" : \"아파트 101동 202호\",\n    \"extraAddress\" : \"배송전 연락주세요.\",\n    \"default\" : false\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "api" ],
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/success-address-delete" : {
                    "value" : "{\n  \"message\" : \"주소 삭제가 완료되었습니다.\",\n  \"data\" : 1\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-default-delete-address" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"기본 주소는 삭제할 수 없습니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-address-id-delete-address" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"해당 주소가 존재하지 않습니다.\"\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api" ],
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "address-controller-test/invalid-default-update-address" : {
                  "value" : "{\n  \"addressName\" : \"새집\",\n  \"postcode\" : \"23456\",\n  \"postAddress\" : \"서울시 강남구\",\n  \"detailAddress\" : \"아파트 202동 303호\",\n  \"extraAddress\" : \"배송전 연락주세요.\",\n  \"default\" : false\n}"
                },
                "address-controller-test/invalid-address-id-update-address" : {
                  "value" : "{\n  \"addressName\" : \"새집\",\n  \"postcode\" : \"23456\",\n  \"postAddress\" : \"서울시 강남구\",\n  \"detailAddress\" : \"아파트 202동 303호\",\n  \"extraAddress\" : \"배송전 연락주세요.\",\n  \"default\" : false\n}"
                },
                "address-controller-test/success-update-address" : {
                  "value" : "{\n  \"addressName\" : \"새집\",\n  \"postcode\" : \"23456\",\n  \"postAddress\" : \"서울시 강남구\",\n  \"detailAddress\" : \"아파트 202동 303호\",\n  \"extraAddress\" : \"배송전 연락주세요.\",\n  \"default\" : false\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-default-update-address" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"기본 주소는 필수입니다\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-address-id-update-address" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"해당 주소가 존재하지 않습니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "address-controller-test/success-update-address" : {
                    "value" : "{\n  \"message\" : \"주소 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"addressId\" : 1,\n    \"addressName\" : \"새집\",\n    \"postcode\" : \"23456\",\n    \"postAddress\" : \"서울시 강남구\",\n    \"detailAddress\" : \"아파트 202동 303호\",\n    \"extraAddress\" : \"배송전 연락주세요.\",\n    \"default\" : false\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "admin-banner-controller-test/",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "admin-banner-controller-test/invalid-date-update-banner" : {
                  "value" : "{\n  \"eventId\" : 1,\n  \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n  \"startDate\" : \"2025-03-08\",\n  \"startTime\" : \"18:00\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"2025-03-09\"\n}"
                },
                "admin-banner-controller-test/invalid-date-create-banner" : {
                  "value" : "{\n  \"eventId\" : 1,\n  \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n  \"startDate\" : \"2025-03-08\",\n  \"startTime\" : \"18:00\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"2025-03-09\"\n}"
                },
                "admin-banner-controller-test/invalid-banner-id-create-banner" : {
                  "value" : "{\n  \"eventId\" : 1,\n  \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n  \"startDate\" : \"2025-03-08\",\n  \"startTime\" : \"18:00\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"19:00\"\n}"
                },
                "admin-banner-controller-test/success-create-banner" : {
                  "value" : "{\n  \"eventId\" : 1,\n  \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n  \"startDate\" : \"2025-03-08\",\n  \"startTime\" : \"18:00\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"19:00\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/invalid-banner-id-create-banner" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"이벤트가 존재하지 않습니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/success-create-banner" : {
                    "value" : "{\n  \"message\" : \"배너 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"bannerId\" : 1,\n    \"eventId\" : 1,\n    \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n    \"startTime\" : \"2025-03-09T18:00\",\n    \"endTime\" : \"2025-03-11T00:00\"\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "chat-controller-test/success-find-chat-list-for-admin",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "admin-coupon-controller-test/success-create-coupon",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "admin-coupon-controller-test/success-create-coupon" : {
                  "value" : "{\n  \"couponName\" : \"1000원 할인쿠폰\",\n  \"discountValue\" : 1000,\n  \"startDate\" : \"2025-03-01\",\n  \"startTime\" : \"00:00\",\n  \"endDate\" : \"2025-03-10\",\n  \"endTime\" : \"00:00\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-create-coupon" : {
                    "value" : "{\n  \"message\" : \"쿠폰 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"couponName\" : \"1000원 할인쿠폰\",\n    \"discountValue\" : 1000\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "admin-delivery-controller-test/",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "admin-delivery-controller-test/invalid-order-id-create-delivery" : {
                  "value" : "{\n  \"orderId\" : 1,\n  \"receiverName\" : \"봉미선\",\n  \"receiverPhone\" : \"010-1234-4567\",\n  \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n  \"trackingNumber\" : \"123456789\",\n  \"courierCompany\" : \"cjgls\"\n}"
                },
                "admin-delivery-controller-test/invalid-order-state-create-delivery" : {
                  "value" : "{\n  \"orderId\" : 1,\n  \"receiverName\" : \"봉미선\",\n  \"receiverPhone\" : \"010-1234-4567\",\n  \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n  \"trackingNumber\" : \"123456789\",\n  \"courierCompany\" : \"cjgls\"\n}"
                },
                "admin-delivery-controller-test/success-create-delivery" : {
                  "value" : "{\n  \"orderId\" : 1,\n  \"receiverName\" : \"봉미선\",\n  \"receiverPhone\" : \"010-1234-4567\",\n  \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n  \"trackingNumber\" : \"123456789\",\n  \"courierCompany\" : \"cjgls\"\n}"
                },
                "admin-delivery-controller-test/conflict-order-id-create-delivery" : {
                  "value" : "{\n  \"orderId\" : 1,\n  \"receiverName\" : \"봉미선\",\n  \"receiverPhone\" : \"010-1234-4567\",\n  \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n  \"trackingNumber\" : \"123456789\",\n  \"courierCompany\" : \"cjgls\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-order-id-create-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"주문이 존재하지 않습니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-order-state-create-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"배송 대기 중인 주문만 등록할 수 있습니다\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/success-create-delivery" : {
                    "value" : "{\n  \"message\" : \"배송 정보 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"deliveryId\" : 2,\n    \"orderId\" : 1,\n    \"receiverName\" : \"봉미선\",\n    \"receiverPhone\" : \"010-1234-4567\",\n    \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n    \"courierCompany\" : \"CJGLS\",\n    \"trackingNumber\" : \"123456789\",\n    \"status\" : \"REGISTERED\",\n    \"createdAt\" : \"2025-03-11T00:00:00\"\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/conflict-order-id-create-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"CONFLICT\",\n  \"errorMessage\" : \"이미 배송정보가 존재하는 주문입니다.\"\n}"
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
        "tags" : [ "api" ],
        "operationId" : "admin-event-controller-test/",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "admin-event-controller-test/invalid-start-time-create-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-22\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-17\",\n  \"endTime\" : \"19:53\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
                },
                "admin-event-controller-test/success-create-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-13\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-19\",\n  \"endTime\" : \"19:53\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
                },
                "admin-event-controller-test/missing-details-create-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-13\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-19\",\n  \"endTime\" : \"19:53\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
                },
                "admin-event-controller-test/invalid-end-time-create-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-13\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"19:00\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
                },
                "admin-event-controller-test/fail-parsing-create-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-13\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-19\",\n  \"endTime\" : \"19:53\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-event-controller-test/invalid-start-time-create-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"이벤트 시작일이 종료일보다 이후입니다.\"\n}"
                  },
                  "admin-event-controller-test/missing-details-create-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"쿠폰 정보가 누락되었습니다.\"\n}"
                  },
                  "admin-event-controller-test/invalid-end-time-create-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"이벤트 종료일이 현재 시간보다 이전입니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-event-controller-test/success-create-event" : {
                    "value" : "{\n  \"message\" : \"이벤트 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"상품 증정 선착순 이벤트\",\n    \"startTime\" : \"2025-03-08T18:00\",\n    \"endTime\" : \"2025-03-09T19:00\"\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "auth-controller-test/",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "auth-controller-test/invalid-password-log-in" : {
                  "value" : "{\n  \"email\" : \"test@mail.com\",\n  \"password\" : \"rawPassword123!\"\n}"
                },
                "auth-controller-test/invalid-email-log-in" : {
                  "value" : "{\n  \"email\" : \"test@mail.com\",\n  \"password\" : \"rawPassword123!\"\n}"
                },
                "auth-controller-test/success-log-in" : {
                  "value" : "{\n  \"email\" : \"test@mail.com\",\n  \"password\" : \"rawPassword123!\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "auth-controller-test/invalid-password-log-in" : {
                    "value" : "{\n  \"httpStatus\" : \"UNAUTHORIZED\",\n  \"errorMessage\" : \"잘못된 아이디 또는 비밀번호입니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "auth-controller-test/invalid-email-log-in" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "auth-controller-test/success-log-in" : {
                    "value" : "{\n  \"message\" : \"로그인이 완료되었습니다.\",\n  \"data\" : {\n    \"accessToken\" : \"accessToken\",\n    \"expiresAt\" : 1741341143\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "auth-controller-test/success-log-out",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "auth-controller-test/success-log-out" : {
                    "value" : "{\n  \"message\" : \"로그아웃이 완료되었습니다.\",\n  \"data\" : 1\n}"
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
        "tags" : [ "api" ],
        "operationId" : "auth-controller-test/",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "auth-controller-test/success-refresh-token" : {
                    "value" : "{\n  \"message\" : \"토큰 발급이 완료되었습니다.\",\n  \"data\" : {\n    \"accessToken\" : \"accessToken\",\n    \"expiresAt\" : 1741341143\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "auth-controller-test/mismatch-info-fail-refresh-token" : {
                    "value" : "{\n  \"httpStatus\" : \"FORBIDDEN\",\n  \"errorMessage\" : \"유효하지 않은 토큰입니다.\"\n}"
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
        "tags" : [ "api" ],
        "operationId" : "auth-controller-test/",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "auth-controller-test/success-sign-up" : {
                  "value" : "{\n  \"email\" : \"test@mail.com\",\n  \"password\" : \"rawPassword123!\",\n  \"nickName\" : \"nickname\"\n}"
                },
                "auth-controller-test/duplicated-email-sign-up" : {
                  "value" : "{\n  \"email\" : \"test@mail.com\",\n  \"password\" : \"rawPassword123!\",\n  \"nickName\" : \"nickname\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "auth-controller-test/success-sign-up" : {
                    "value" : "{\n  \"message\" : \"회원가입이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"email\" : \"test@mail.com\",\n    \"nickname\" : \"nickname\"\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "auth-controller-test/duplicated-email-sign-up" : {
                    "value" : "{\n  \"httpStatus\" : \"CONFLICT\",\n  \"errorMessage\" : \"이미 사용중인 이메일 입니다.\"\n}"
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
        "tags" : [ "api" ],
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "banner-controller-test/invalid-banner-id-get-banner" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "banner-controller-test/success-get-banner" : {
                    "value" : "{\n  \"message\" : \"배너 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"bannerId\" : 1,\n    \"eventId\" : 1,\n    \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n    \"startTime\" : \"2025-03-09T18:00\",\n    \"endTime\" : \"2025-03-11T00:00\"\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "cart-controller-test/success-add-item",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
    "/api/cocktails/search" : {
      "get" : {
        "tags" : [ "api", "Cocktail API" ],
        "summary" : "키워드를 통한 칵테일 검색",
        "description" : "키워드를 통한 칵테일 검색",
        "operationId" : "cocktail",
        "parameters" : [ {
          "name" : "page",
          "in" : "query",
          "description" : "페이지 넘버",
          "required" : false,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "size",
          "in" : "query",
          "description" : "페이지당 항목 수",
          "required" : false,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "isCraftable",
          "in" : "query",
          "description" : "조주 가능한 칵테일만 검색",
          "required" : false,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "recipeType",
          "in" : "query",
          "description" : "OFFICIAL/CUSTOM",
          "required" : false,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "cocktail-controller-test/get-craftable-cocktail" : {
                  "value" : "{\n  \"ingredientIds\" : [ ]\n}"
                },
                "cocktail-controller-test/get-cocktail-by-ingr" : {
                  "value" : "{\n  \"ingredientIds\" : [ ]\n}"
                },
                "cocktail/getCraftableCocktail" : {
                  "value" : "{\n  \"ingredientIds\" : [ ]\n}"
                },
                "cocktail/getCocktailByIngr" : {
                  "value" : "{\n  \"ingredientIds\" : [ ]\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
    "/api/coupons/users" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "coupon-controller-test/success-get-",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-empty-user-coupons-with-issued" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"userCoupons\" : [ {\n      \"userCouponId\" : 1,\n      \"userId\" : 1,\n      \"coupon\" : {\n        \"id\" : 1,\n        \"couponName\" : \"1000원 할인쿠폰\",\n        \"discountValue\" : 1000\n      },\n      \"status\" : \"ISSUED\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-user-coupons" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"userCoupons\" : [ {\n      \"userCouponId\" : 1,\n      \"userId\" : 1,\n      \"coupon\" : {\n        \"id\" : 1,\n        \"couponName\" : \"1000원 할인쿠폰\",\n        \"discountValue\" : 1000\n      },\n      \"status\" : \"ISSUED\"\n    } ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
                  },
                  "coupon-controller-test/success-get-empty-user-coupons" : {
                    "value" : "{\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"userCoupons\" : [ ],\n    \"currentPage\" : 0,\n    \"totalPages\" : 1,\n    \"hasNext\" : false\n  }\n}"
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
        "tags" : [ "api" ],
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "coupon-controller-test/invalid-coupon-id-get-coupon" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-coupon" : {
                    "value" : "{\n  \"message\" : \"쿠폰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"couponName\" : \"1000원 할인쿠폰\",\n    \"discountValue\" : 1000,\n    \"startTime\" : \"2025-03-01T00:00\",\n    \"endTime\" : \"2025-03-10T00:00\"\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "event-controller-test/",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "event-controller-test/success-get-event" : {
                    "value" : "{\n  \"message\" : \"이벤트 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"상품 증정 선착순 이벤트\",\n    \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n    \"startTime\" : \"2025-03-08T18:00\",\n    \"endTime\" : \"2025-03-09T19:00\",\n    \"eventDetail\" : {\n      \"couponId\" : 1,\n      \"couponName\" : \"20% 할인 쿠폰\"\n    }\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "event-controller-test/invalid-event-id-get-event" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"이벤트가 존재하지 않습니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "event-controller-test/fail-parsing-json-node-get-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"JSON 데이터 검증 중 오류가 발생했습니다.\"\n}"
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
        "tags" : [ "api" ],
        "operationId" : "favorite-controller-test/",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "favorite-controller-test/invalid-id-delete-favorite" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-delete-favorite" : {
                    "value" : "{\n  \"message\" : \"즐겨찾기 해제가 완료되었습니다.\",\n  \"data\" : {\n    \"cocktailId\" : 1,\n    \"name\" : \"블랙 러시안\",\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n    \"type\" : \"OFFICIAL\",\n    \"favoritedAt\" : \"2025-03-10T01:01:00\"\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "ingredient-controller-test/조건별_재료조회_성공",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "ingredient-controller-test/조건별_재료조회_성공" : {
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
        "tags" : [ "api" ],
        "operationId" : "ingredient-controller-test/재료_단건조회_성공",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료_단건조회_성공" : {
                    "value" : "{\n  \"message\" : \"재료 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"보드카\",\n    \"category\" : \"VODKA\",\n    \"imageUrl\" : \"대충 이미지 주소\",\n    \"avb\" : 40.0\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api" ],
        "operationId" : "ingredient-controller-test/재료수정_성공",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료수정_성공" : {
                    "value" : "{\n  \"message\" : \"재료 정보 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : null,\n    \"name\" : \"깔루아\",\n    \"description\" : \"맛있는 깔루아\",\n    \"category\" : \"OTHER\",\n    \"imageUrl\" : \"url\",\n    \"avb\" : 2.0\n  }\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/ingredients/{ingreientId}" : {
      "delete" : {
        "tags" : [ "api" ],
        "operationId" : "ingredient-controller-test/재료삭제_성공",
        "parameters" : [ {
          "name" : "ingreientId",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "ingredient-controller-test/재료삭제_성공" : {
                    "value" : "{\n  \"message\" : \"재료 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
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
        "tags" : [ "api" ],
        "operationId" : "order-controller-test/success-get-order",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "order-controller-test/success-delete-order",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "order-controller-test/success-update-order",
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
    "/api/payments/confirm" : {
      "post" : {
        "tags" : [ "api" ],
        "operationId" : "payment-controller-test/success-confirm-payment",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "payment-controller-test/success-find-payment",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "product-controller-test/success-get-product",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "product-controller-test/success-get-product" : {
                    "value" : "{\n  \"message\" : \"상품 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"productId\" : 1,\n    \"name\" : \"보드카\",\n    \"description\" : \"독한 술입니다.\",\n    \"price\" : 120000,\n    \"stockQuantity\" : 50\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "delete" : {
        "tags" : [ "api" ],
        "operationId" : "product-controller-test/success-delete-product",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "product-controller-test/success-delete-product" : {
                    "value" : "{\n  \"message\" : \"상품 삭제가 완료되었습니다.\",\n  \"data\" : null\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api" ],
        "operationId" : "product-controller-test/success-update-product",
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "product-controller-test/success-update-product" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "product-controller-test/success-update-product" : {
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
        "tags" : [ "api" ],
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/invalid-banner-id-delete-banner" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/success-delete-banner" : {
                    "value" : "{\n  \"message\" : \"배너 삭제가 완료되었습니다.\",\n  \"data\" : 1\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api" ],
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "admin-banner-controller-test/invalid-event-id-update-banner" : {
                  "value" : "{\n  \"eventId\" : 1,\n  \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n  \"startDate\" : \"2025-03-08\",\n  \"startTime\" : \"18:00\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"19:00\"\n}"
                },
                "admin-banner-controller-test/invalid-banner-id-update-banner" : {
                  "value" : "{\n  \"eventId\" : 1,\n  \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n  \"startDate\" : \"2025-03-08\",\n  \"startTime\" : \"18:00\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"19:00\"\n}"
                },
                "admin-banner-controller-test/success-update-banner" : {
                  "value" : "{\n  \"eventId\" : 1,\n  \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n  \"startDate\" : \"2025-03-08\",\n  \"startTime\" : \"18:00\",\n  \"endDate\" : \"2025-03-09\",\n  \"endTime\" : \"19:00\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/invalid-event-id-update-banner" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"이벤트가 존재하지 않습니다.\"\n}"
                  },
                  "admin-banner-controller-test/invalid-banner-id-update-banner" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-banner-controller-test/success-update-banner" : {
                    "value" : "{\n  \"message\" : \"배너 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"bannerId\" : 1,\n    \"eventId\" : 1,\n    \"imageUrl\" : \"https://via.placeholder.com/1200x300/FF5733/FFFFFF?text=Event+Banner+1\",\n    \"startTime\" : \"2025-03-09T18:00\",\n    \"endTime\" : \"2025-03-11T00:00\"\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "chat-controller-test/success-find-chat-for-admin",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/invalid-coupon-id-delete-coupon" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-delete-coupon" : {
                    "value" : "{\n  \"message\" : \"쿠폰 삭제가 완료되었습니다.\",\n  \"data\" : 1\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api" ],
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "admin-coupon-controller-test/invalid-coupon-id-update-coupon" : {
                  "value" : "{\n  \"couponName\" : \"1000원 할인쿠폰\",\n  \"discountValue\" : 1000,\n  \"startDate\" : \"2025-03-01\",\n  \"startTime\" : \"00:00\",\n  \"endDate\" : \"2025-03-10\",\n  \"endTime\" : \"00:00\"\n}"
                },
                "admin-coupon-controller-test/success-update-coupon" : {
                  "value" : "{\n  \"couponName\" : \"1000원 할인쿠폰\",\n  \"discountValue\" : 1000,\n  \"startDate\" : \"2025-03-01\",\n  \"startTime\" : \"00:00\",\n  \"endDate\" : \"2025-03-10\",\n  \"endTime\" : \"00:00\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/invalid-coupon-id-update-coupon" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-update-coupon" : {
                    "value" : "{\n  \"message\" : \"쿠폰 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"couponName\" : \"1000원 할인쿠폰\",\n    \"discountValue\" : 1000,\n    \"startTime\" : \"2025-03-01T00:00\",\n    \"endTime\" : \"2025-03-10T00:00\"\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "admin-delivery-controller-test/",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-delivery-id-get-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"배송 정보가 존재하지 않습니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/success-get-delivery" : {
                    "value" : "{\n  \"message\" : \"배송 정보 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"deliveryId\" : 2,\n    \"orderId\" : 1,\n    \"receiverName\" : \"봉미선\",\n    \"receiverPhone\" : \"010-1234-4567\",\n    \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n    \"courierCompany\" : \"CJGLS\",\n    \"trackingNumber\" : \"123456789\",\n    \"status\" : \"REGISTERED\",\n    \"createdAt\" : \"2025-03-11T00:00:00\"\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api" ],
        "operationId" : "admin-delivery-controller-test/",
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-delivery-status-update-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"유효하지 않은 배송 상태 변경입니다\"\n}"
                  },
                  "admin-delivery-controller-test/conflict-delivery-status-update-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"이미 해당 배송 상태입니다\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/invalid-delivery-id-update-delivery" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"배송 정보가 존재하지 않습니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-delivery-controller-test/success-update-delivery" : {
                    "value" : "{\n  \"message\" : \"배송 정보 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"deliveryId\" : 2,\n    \"orderId\" : 1,\n    \"receiverName\" : \"봉미선\",\n    \"receiverPhone\" : \"010-1234-4567\",\n    \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\",\n    \"courierCompany\" : \"CJGLS\",\n    \"trackingNumber\" : \"123456789\",\n    \"status\" : \"REGISTERED\",\n    \"createdAt\" : \"2025-03-11T00:00:00\"\n  }\n}"
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
        "tags" : [ "api" ],
        "operationId" : "admin-event-controller-test/",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "admin-event-controller-test/",
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "admin-event-controller-test/invalid-banner-id-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-13\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-19\",\n  \"endTime\" : \"19:53\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
                },
                "admin-event-controller-test/success-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-13\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-19\",\n  \"endTime\" : \"19:53\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
                },
                "admin-event-controller-test/missing-details-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-13\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-19\",\n  \"endTime\" : \"19:53\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
                },
                "admin-event-controller-test/fail-parsing-json-node-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-13\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-19\",\n  \"endTime\" : \"19:53\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
                },
                "admin-event-controller-test/fail-parsing-update-event" : {
                  "value" : "{\n  \"name\" : \"상품 증정 선착순 이벤트\",\n  \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n  \"startDate\" : \"2025-03-13\",\n  \"startTime\" : \"19:53\",\n  \"endDate\" : \"2025-03-19\",\n  \"endTime\" : \"19:53\",\n  \"eventType\" : \"fcfs\",\n  \"limitCount\" : 10,\n  \"eventDetailData\" : {\n    \"couponId\" : 1,\n    \"couponName\" : \"20% 할인 쿠폰\"\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-event-controller-test/success-update-event" : {
                    "value" : "{\n  \"message\" : \"이벤트 수정이 완료되었습니다.\",\n  \"data\" : {\n    \"id\" : 1,\n    \"name\" : \"상품 증정 선착순 이벤트\",\n    \"description\" : \"선착순 10명에게 소정의 상품을 드립니다\",\n    \"startTime\" : \"2025-03-08T18:00\",\n    \"endTime\" : \"2025-03-09T19:00\",\n    \"eventDetail\" : {\n      \"couponId\" : 1,\n      \"couponName\" : \"20% 할인 쿠폰\"\n    }\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "cart-controller-test/success-remove-item",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "cart-controller-test/success-update-item-quantity",
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "favorite-controller-test/success-check-favorite",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-check-favorite" : {
                    "value" : "{\n  \"message\" : \"즐겨찾기 상태 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"favorited\" : true\n  }\n}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "api" ],
        "operationId" : "favorite-controller-test/",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "favorite-controller-test/duplicated-favorite-create-favorite" : {
                    "value" : "{\n  \"httpStatus\" : \"CONFLICT\",\n  \"errorMessage\" : \"이미 즐겨찾기한 레시피입니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "favorite-controller-test/invalid-favorite-id-create-favorite" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"칵테일이 존재하지 않습니다.\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-create-favorite" : {
                    "value" : "{\n  \"message\" : \"즐겨찾기 등록이 완료되었습니다.\",\n  \"data\" : {\n    \"cocktailId\" : 1,\n    \"name\" : \"블랙 러시안\",\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\n    \"type\" : \"OFFICIAL\",\n    \"favoritedAt\" : \"2025-03-10T01:01:00\"\n  }\n}"
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
        "tags" : [ "api" ],
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
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-user-coupon" : {
                    "value" : "{\n  \"message\" : \"쿠폰 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"userCouponId\" : 1,\n    \"userId\" : 1,\n    \"coupon\" : {\n      \"id\" : 1,\n      \"couponName\" : \"1000원 할인쿠폰\",\n      \"discountValue\" : 1000\n    },\n    \"status\" : \"ISSUED\",\n    \"usedTime\" : null\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "coupon-controller-test/invalid-user-coupon-id-get-user-coupon" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\n}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "api" ],
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-use-user-coupon" : {
                    "value" : "{\n  \"message\" : \"쿠폰 사용이 완료되었습니다.\",\n  \"data\" : {\n    \"userCouponId\" : 1,\n    \"userId\" : 1,\n    \"coupon\" : {\n      \"id\" : 1,\n      \"couponName\" : \"1000원 할인쿠폰\",\n      \"discountValue\" : 1000\n    },\n    \"status\" : \"ISSUED\",\n    \"usedTime\" : null\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "coupon-controller-test/invalid-user-coupon-id-use-user-coupon" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\n}"
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
        "tags" : [ "api" ],
        "operationId" : "delivery-controller-test/",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "delivery-controller-test/success-get-shipping-for-user" : {
                    "value" : "{\n  \"message\" : \"배송 정보 조회가 완료되었습니다.\",\n  \"data\" : {\n    \"deliveryId\" : 2,\n    \"orderId\" : 1,\n    \"courierName\" : \"대한통운\",\n    \"trackingNumber\" : \"123456789\",\n    \"status\" : \"REGISTERED\",\n    \"deliveryMessage\" : \"현관 비밀번호는 #0000#입니다.\"\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "delivery-controller-test/forbidden-order-id-get-shipping-for-user" : {
                    "value" : "{\n  \"httpStatus\" : \"FORBIDDEN\",\n  \"errorMessage\" : \"접근 권한이 없습니다.\"\n}"
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
        "tags" : [ "api" ],
        "operationId" : "event-controller-test/",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "event-controller-test/success-participate-event" : {
                    "value" : "{\n  \"message\" : \"이벤트 참여를 완료했습니다.\",\n  \"data\" : {\n    \"eventId\" : 1,\n    \"userId\" : 1,\n    \"couponId\" : 1,\n    \"eventResult\" : \"WINNER\"\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "event-controller-test/invalid-status-participate-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"유효하지 않은 이벤트 상태입니다.\"\n}"
                  },
                  "event-controller-test/missing-details-participate-event" : {
                    "value" : "{\n  \"httpStatus\" : \"BAD_REQUEST\",\n  \"errorMessage\" : \"쿠폰 정보가 누락되었습니다.\"\n}"
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
        "tags" : [ "api" ],
        "operationId" : "ingredient-review-controller-test/success-get-my-ingredient-review",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "ingredient-review-controller-test/success-delete-ingredient-review",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "ingredient-review-controller-test/success-update-ingredient-review",
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "ingredient-review-controller-test/success-get-recipe-review",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "ingredient-review-controller-test/success-get-all-ingredient-review",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
        "operationId" : "ingredient-review-controller-test/success-create-ingredient-review",
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
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
        "tags" : [ "api" ],
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "admin-coupon-controller-test/success-grant-user-coupon" : {
                  "value" : "{\n  \"userId\" : 1\n}"
                },
                "admin-coupon-controller-test/invalid-coupon-id-grant-user-coupon" : {
                  "value" : "{\n  \"userId\" : 1\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-grant-user-coupon" : {
                    "value" : "{\n  \"message\" : \"쿠폰 지급이 완료되었습니다.\",\n  \"data\" : {\n    \"userCouponId\" : 1,\n    \"userId\" : 1,\n    \"coupon\" : {\n      \"id\" : 1,\n      \"couponName\" : \"1000원 할인쿠폰\",\n      \"discountValue\" : 1000\n    },\n    \"status\" : \"ISSUED\"\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/invalid-coupon-id-grant-user-coupon" : {
                    "value" : "{\n  \"httpStatus\" : \"NOT_FOUND\",\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\n}"
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
        "tags" : [ "api" ],
        "operationId" : "admin-event-controller-test/",
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
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
        "tags" : [ "api" ],
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
                "$ref" : "#/components/schemas/api-coupons-users486549215"
              },
              "examples" : {
                "super-admin-user-controller-test/success-update-user-role" : {
                  "value" : "{\n  \"userRole\" : \"ADMIN\"\n}"
                },
                "super-admin-user-controller-test/duplicated-user-role-update-user-role" : {
                  "value" : "{\n  \"userRole\" : \"ADMIN\"\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "super-admin-user-controller-test/success-update-user-role" : {
                    "value" : "{\n  \"message\" : \"권한 변경이 완료되었습니다.\",\n  \"data\" : {\n    \"userId\" : 1,\n    \"userRole\" : \"ADMIN\"\n  }\n}"
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
                  "$ref" : "#/components/schemas/api-coupons-users486549215"
                },
                "examples" : {
                  "super-admin-user-controller-test/duplicated-user-role-update-user-role" : {
                    "value" : "{\n  \"httpStatus\" : \"CONFLICT\",\n  \"errorMessage\" : \"동일한 권한입니다.\"\n}"
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
      "api-coupons-users486549215" : {
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