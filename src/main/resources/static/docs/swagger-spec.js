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
    "url" : "http://대충 배포중인 주소"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/success-get-addresses" : {
                    "value" : "{\r\n  \"message\" : \"주소지 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"addresses\" : [ {\r\n      \"addressId\" : 1,\r\n      \"addressName\" : \"집\",\r\n      \"postAddress\" : \"12345\",\r\n      \"detailAddress\" : \"서울시 강서구\",\r\n      \"default\" : false\r\n    }, {\r\n      \"addressId\" : 1,\r\n      \"addressName\" : \"집\",\r\n      \"postAddress\" : \"12345\",\r\n      \"detailAddress\" : \"서울시 강서구\",\r\n      \"default\" : false\r\n    } ],\r\n    \"hasNext\" : true,\r\n    \"nextCursor\" : 2\r\n  }\r\n}"
                  },
                  "address-controller-test/success-get-addresses-empty-list" : {
                    "value" : "{\r\n  \"message\" : \"주소지 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"addresses\" : [ ],\r\n    \"hasNext\" : false,\r\n    \"nextCursor\" : null\r\n  }\r\n}"
                  },
                  "address-controller-test/success-get-addresses-first-page" : {
                    "value" : "{\r\n  \"message\" : \"주소지 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"addresses\" : [ {\r\n      \"addressId\" : 1,\r\n      \"addressName\" : \"집\",\r\n      \"postAddress\" : \"12345\",\r\n      \"detailAddress\" : \"서울시 강서구\",\r\n      \"default\" : false\r\n    } ],\r\n    \"hasNext\" : false,\r\n    \"nextCursor\" : 1\r\n  }\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "address-controller-test/success-create-address" : {
                  "value" : "{\r\n  \"addressName\" : \"집\",\r\n  \"postcode\" : \"12345\",\r\n  \"postAddress\" : \"서울시 강서구\",\r\n  \"detailAddress\" : \"아파트 101동 202호\",\r\n  \"extraAddress\" : \"배송전 연락주세요.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/success-create-address" : {
                    "value" : "{\r\n  \"message\" : \"주소 등록이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"addressId\" : 1,\r\n    \"addressName\" : \"집\",\r\n    \"postcode\" : \"12345\",\r\n    \"postAddress\" : \"서울시 강서구\",\r\n    \"detailAddress\" : \"아파트 101동 202호\",\r\n    \"extraAddress\" : \"배송전 연락주세요.\",\r\n    \"default\" : false\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "cart-controller-test/success-find-cart" : {
                    "value" : "{\r\n  \"message\" : \"장바구니 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"itemList\" : [ {\r\n      \"cartItemId\" : 1,\r\n      \"product\" : {\r\n        \"productId\" : 1,\r\n        \"name\" : \"보드카\",\r\n        \"description\" : \"독한 술입니다.\",\r\n        \"price\" : 120000,\r\n        \"stockQuantity\" : 50\r\n      },\r\n      \"quantity\" : 3\r\n    } ]\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "cart-controller-test/success-empty-cart" : {
                    "value" : "{\r\n  \"message\" : \"장바구니 비우기가 완료되었습니다.\",\r\n  \"data\" : null\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "chat-controller-test/success-find-chat" : {
                    "value" : "{\r\n  \"messageList\" : [ {\r\n    \"sender\" : \"user\",\r\n    \"content\" : \"메시지 입니다.\",\r\n    \"timeMillis\" : 1741314450785\r\n  } ]\r\n}"
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
        "operationId" : "cocktail-controller-test/get-cocktails",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "cocktail-controller-test/get-cocktails" : {
                    "value" : "{\r\n  \"message\" : \"칵테일 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"content\" : [ {\r\n      \"id\" : 1,\r\n      \"name\" : \"블랙 러시안\",\r\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\r\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\r\n      \"type\" : \"CUSTOM\",\r\n      \"user\" : {\r\n        \"id\" : 1,\r\n        \"email\" : \"test@mail.com\",\r\n        \"nickname\" : \"nickname\",\r\n        \"userRole\" : \"USER\"\r\n      },\r\n      \"ingredientList\" : [ {\r\n        \"id\" : 1,\r\n        \"name\" : \"보드카\",\r\n        \"description\" : \"보드카\",\r\n        \"category\" : \"VODKA\",\r\n        \"imageUrl\" : \"대충 이미지 주소\",\r\n        \"avb\" : 40.0\r\n      }, {\r\n        \"id\" : 4,\r\n        \"name\" : \"깔루아 오리지널\",\r\n        \"description\" : \"커피 리큐르\",\r\n        \"category\" : \"OTHER\",\r\n        \"imageUrl\" : \"대충 이미지 주소\",\r\n        \"avb\" : 16.0\r\n      } ],\r\n      \"likeCount\" : 0,\r\n      \"starRate\" : 0.0,\r\n      \"viewCount\" : 0,\r\n      \"createdAt\" : null,\r\n      \"modifiedAt\" : null\r\n    }, {\r\n      \"id\" : 2,\r\n      \"name\" : \"화이트 러시안\",\r\n      \"description\" : \"블랙 러시안에 우유를 더한 칵테일\",\r\n      \"recipe\" : \"1.칠링한 온더락 글라스에 보드카, 깔루아를 빌드한다.\\n2.우유 혹은 생크림을 조심스레 붓는다.\\n3.살짝 저어준다.\",\r\n      \"type\" : \"OFFICIAL\",\r\n      \"user\" : {\r\n        \"id\" : 1,\r\n        \"email\" : \"test@mail.com\",\r\n        \"nickname\" : \"nickname\",\r\n        \"userRole\" : \"USER\"\r\n      },\r\n      \"ingredientList\" : [ {\r\n        \"id\" : 1,\r\n        \"name\" : \"보드카\",\r\n        \"description\" : \"보드카\",\r\n        \"category\" : \"VODKA\",\r\n        \"imageUrl\" : \"대충 이미지 주소\",\r\n        \"avb\" : 40.0\r\n      }, {\r\n        \"id\" : 4,\r\n        \"name\" : \"깔루아 오리지널\",\r\n        \"description\" : \"커피 리큐르\",\r\n        \"category\" : \"OTHER\",\r\n        \"imageUrl\" : \"대충 이미지 주소\",\r\n        \"avb\" : 16.0\r\n      }, {\r\n        \"id\" : 5,\r\n        \"name\" : \"우유\",\r\n        \"description\" : \"어릴 때 좀 많이 마실 걸\",\r\n        \"category\" : \"OTHER\",\r\n        \"imageUrl\" : \"대충 이미지 주소\",\r\n        \"avb\" : 0.0\r\n      } ],\r\n      \"likeCount\" : 0,\r\n      \"starRate\" : 0.0,\r\n      \"viewCount\" : 0,\r\n      \"createdAt\" : null,\r\n      \"modifiedAt\" : null\r\n    }, {\r\n      \"id\" : 3,\r\n      \"name\" : \"깔루아 밀크\",\r\n      \"description\" : \"달달구리한 커피 칵테일\",\r\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\r\n      \"type\" : \"OFFICIAL\",\r\n      \"user\" : {\r\n        \"id\" : 1,\r\n        \"email\" : \"test@mail.com\",\r\n        \"nickname\" : \"nickname\",\r\n        \"userRole\" : \"USER\"\r\n      },\r\n      \"ingredientList\" : [ {\r\n        \"id\" : 4,\r\n        \"name\" : \"깔루아 오리지널\",\r\n        \"description\" : \"커피 리큐르\",\r\n        \"category\" : \"OTHER\",\r\n        \"imageUrl\" : \"대충 이미지 주소\",\r\n        \"avb\" : 16.0\r\n      }, {\r\n        \"id\" : 5,\r\n        \"name\" : \"우유\",\r\n        \"description\" : \"어릴 때 좀 많이 마실 걸\",\r\n        \"category\" : \"OTHER\",\r\n        \"imageUrl\" : \"대충 이미지 주소\",\r\n        \"avb\" : 0.0\r\n      } ],\r\n      \"likeCount\" : 0,\r\n      \"starRate\" : 0.0,\r\n      \"viewCount\" : 0,\r\n      \"createdAt\" : null,\r\n      \"modifiedAt\" : null\r\n    } ],\r\n    \"pageable\" : \"INSTANCE\",\r\n    \"totalPages\" : 1,\r\n    \"totalElements\" : 3,\r\n    \"last\" : true,\r\n    \"size\" : 3,\r\n    \"number\" : 0,\r\n    \"sort\" : {\r\n      \"empty\" : true,\r\n      \"sorted\" : false,\r\n      \"unsorted\" : true\r\n    },\r\n    \"first\" : true,\r\n    \"numberOfElements\" : 3,\r\n    \"empty\" : false\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-coupons" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"coupons\" : [ {\r\n      \"id\" : 1,\r\n      \"couponName\" : \"1000원 할인쿠폰\",\r\n      \"discountValue\" : 1000\r\n    } ],\r\n    \"currentPage\" : 0,\r\n    \"totalPages\" : 1,\r\n    \"hasNext\" : false\r\n  }\r\n}"
                  },
                  "coupon-controller-test/success-get-empty-coupons" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"coupons\" : [ ],\r\n    \"currentPage\" : 0,\r\n    \"totalPages\" : 1,\r\n    \"hasNext\" : false\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-get-empty-favorites" : {
                    "value" : "{\r\n  \"message\" : \"즐겨찾기 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"favorites\" : [ ],\r\n    \"currentPage\" : 0,\r\n    \"totalPages\" : 1,\r\n    \"hasNext\" : false\r\n  }\r\n}"
                  },
                  "favorite-controller-test/success-get-favorites" : {
                    "value" : "{\r\n  \"message\" : \"즐겨찾기 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"favorites\" : [ {\r\n      \"cocktailId\" : 1,\r\n      \"name\" : \"블랙 러시안\",\r\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\r\n      \"type\" : \"CUSTOM\",\r\n      \"favoritedAt\" : \"2025-03-10T01:01:00\"\r\n    } ],\r\n    \"currentPage\" : 0,\r\n    \"totalPages\" : 1,\r\n    \"hasNext\" : false\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "order-controller-test/success-get-all-order" : {
                    "value" : "{\r\n  \"message\" : \"주문 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"content\" : [ {\r\n      \"orderId\" : 1,\r\n      \"orderNumber\" : \"23031114553289\",\r\n      \"name\" : \"보드카 외 2개\",\r\n      \"amount\" : 120000,\r\n      \"status\" : \"PENDING_PAYMENT\",\r\n      \"orderDate\" : \"2025-03-10T16:08:17.783333\"\r\n    } ],\r\n    \"pageable\" : \"INSTANCE\",\r\n    \"totalPages\" : 1,\r\n    \"totalElements\" : 1,\r\n    \"last\" : true,\r\n    \"size\" : 1,\r\n    \"number\" : 0,\r\n    \"sort\" : {\r\n      \"empty\" : true,\r\n      \"sorted\" : false,\r\n      \"unsorted\" : true\r\n    },\r\n    \"first\" : true,\r\n    \"numberOfElements\" : 1,\r\n    \"empty\" : false\r\n  }\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "order-controller-test/success-create-order" : {
                  "value" : "{\r\n  \"addressId\" : 1\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "order-controller-test/success-create-order" : {
                    "value" : "{\r\n  \"message\" : \"주문이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"orderId\" : 1,\r\n    \"orderNumber\" : \"23031114553289\",\r\n    \"name\" : \"보드카 외 2개\",\r\n    \"amount\" : 120000,\r\n    \"status\" : \"PENDING_PAYMENT\",\r\n    \"orderDate\" : \"2025-03-10T16:08:17.783333\"\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "product-controller-test/success-get-all-product" : {
                    "value" : "{\r\n  \"message\" : \"상품 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"content\" : [ {\r\n      \"productId\" : 1,\r\n      \"name\" : \"보드카\",\r\n      \"description\" : \"독한 술입니다.\",\r\n      \"price\" : 120000,\r\n      \"stockQuantity\" : 50\r\n    } ],\r\n    \"pageable\" : \"INSTANCE\",\r\n    \"totalPages\" : 1,\r\n    \"totalElements\" : 1,\r\n    \"last\" : true,\r\n    \"size\" : 1,\r\n    \"number\" : 0,\r\n    \"sort\" : {\r\n      \"empty\" : true,\r\n      \"sorted\" : false,\r\n      \"unsorted\" : true\r\n    },\r\n    \"first\" : true,\r\n    \"numberOfElements\" : 1,\r\n    \"empty\" : false\r\n  }\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "product-controller-test/success-create-product" : {
                  "value" : "{\r\n  \"name\" : \"보드카\",\r\n  \"description\" : \"독한 술입니다.\",\r\n  \"category\" : \"ALCOHOL\",\r\n  \"price\" : 120000,\r\n  \"stockQuantity\" : 50,\r\n  \"status\" : \"SALE\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "product-controller-test/success-create-product" : {
                    "value" : "{\r\n  \"message\" : \"상품 등록이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"productId\" : 1,\r\n    \"name\" : \"보드카\",\r\n    \"description\" : \"독한 술입니다.\",\r\n    \"price\" : 120000,\r\n    \"stockQuantity\" : 50\r\n  }\r\n}"
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
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "user-controller-test/invalid-user-id-get-user-info" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"유저가 존재하지 않습니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "user-controller-test/success-get-user-info" : {
                    "value" : "{\r\n  \"message\" : \"사용자 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"id\" : 1,\r\n    \"email\" : \"test@mail.com\",\r\n    \"nickname\" : \"nickname\",\r\n    \"userRole\" : \"USER\"\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "user-controller-test/invalid-user-id-delete-user" : {
                    "value" : "{\r\n  \"httpStatus\" : \"CONFLICT\",\r\n  \"errorMessage\" : \"이미 탈퇴한 사용자입니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "user-controller-test/success-delete-user" : {
                    "value" : "{\r\n  \"message\" : \"사용자 삭제가 완료되었습니다.\",\r\n  \"data\" : 1\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "user-controller-test/invalid-user-id-update-user-info" : {
                  "value" : "{\r\n  \"nickname\" : \"nickname\",\r\n  \"password\" : \"rawPassword123!\"\r\n}"
                },
                "user-controller-test/success-update-user-info" : {
                  "value" : "{\r\n  \"nickname\" : \"nickname\",\r\n  \"password\" : \"rawPassword123!\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "user-controller-test/invalid-user-id-update-user-info" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"유저가 존재하지 않습니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "user-controller-test/success-update-user-info" : {
                    "value" : "{\r\n  \"message\" : \"사용자 수정이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"id\" : 1,\r\n    \"email\" : \"test@mail.com\",\r\n    \"nickname\" : \"nickname\",\r\n    \"userRole\" : \"USER\"\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-address-id-get-address" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"해당 주소가 존재하지 않습니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/success-get-address" : {
                    "value" : "{\r\n  \"message\" : \"주소 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"addressId\" : 1,\r\n    \"addressName\" : \"집\",\r\n    \"postcode\" : \"12345\",\r\n    \"postAddress\" : \"서울시 강서구\",\r\n    \"detailAddress\" : \"아파트 101동 202호\",\r\n    \"extraAddress\" : \"배송전 연락주세요.\",\r\n    \"default\" : false\r\n  }\r\n}"
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
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-address-id-delete-address" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"해당 주소가 존재하지 않습니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-default-delete-address" : {
                    "value" : "{\r\n  \"httpStatus\" : \"BAD_REQUEST\",\r\n  \"errorMessage\" : \"기본 주소는 삭제할 수 없습니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/success-address-delete" : {
                    "value" : "{\r\n  \"message\" : \"주소 삭제가 완료되었습니다.\",\r\n  \"data\" : 1\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "address-controller-test/invalid-address-id-update-address" : {
                  "value" : "{\r\n  \"addressName\" : \"새집\",\r\n  \"postcode\" : \"23456\",\r\n  \"postAddress\" : \"서울시 강남구\",\r\n  \"detailAddress\" : \"아파트 202동 303호\",\r\n  \"extraAddress\" : \"배송전 연락주세요.\",\r\n  \"default\" : false\r\n}"
                },
                "address-controller-test/invalid-default-update-address" : {
                  "value" : "{\r\n  \"addressName\" : \"새집\",\r\n  \"postcode\" : \"23456\",\r\n  \"postAddress\" : \"서울시 강남구\",\r\n  \"detailAddress\" : \"아파트 202동 303호\",\r\n  \"extraAddress\" : \"배송전 연락주세요.\",\r\n  \"default\" : false\r\n}"
                },
                "address-controller-test/success-update-address" : {
                  "value" : "{\r\n  \"addressName\" : \"새집\",\r\n  \"postcode\" : \"23456\",\r\n  \"postAddress\" : \"서울시 강남구\",\r\n  \"detailAddress\" : \"아파트 202동 303호\",\r\n  \"extraAddress\" : \"배송전 연락주세요.\",\r\n  \"default\" : false\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-address-id-update-address" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"해당 주소가 존재하지 않습니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/invalid-default-update-address" : {
                    "value" : "{\r\n  \"httpStatus\" : \"BAD_REQUEST\",\r\n  \"errorMessage\" : \"기본 주소는 필수입니다\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "address-controller-test/success-update-address" : {
                    "value" : "{\r\n  \"message\" : \"주소 수정이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"addressId\" : 1,\r\n    \"addressName\" : \"새집\",\r\n    \"postcode\" : \"23456\",\r\n    \"postAddress\" : \"서울시 강남구\",\r\n    \"detailAddress\" : \"아파트 202동 303호\",\r\n    \"extraAddress\" : \"배송전 연락주세요.\",\r\n    \"default\" : false\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "chat-controller-test/success-find-chat-list-for-admin" : {
                    "value" : "{\r\n  \"chatList\" : [ {\r\n    \"userId\" : 2,\r\n    \"lastMessage\" : \"u::메시지 입니다.::1741314450785\"\r\n  } ],\r\n  \"nextCursor\" : \"\"\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "admin-coupon-controller-test/success-create-coupon" : {
                  "value" : "{\r\n  \"couponName\" : \"1000원 할인쿠폰\",\r\n  \"discountValue\" : 1000,\r\n  \"startDate\" : \"2025-03-01\",\r\n  \"startTime\" : \"00:00\",\r\n  \"endDate\" : \"2025-04-01\",\r\n  \"endTime\" : \"00:00\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-create-coupon" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 등록이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"id\" : 1,\r\n    \"couponName\" : \"1000원 할인쿠폰\",\r\n    \"discountValue\" : 1000\r\n  }\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "auth-controller-test/invalid-email-log-in" : {
                  "value" : "{\r\n  \"email\" : \"test@mail.com\",\r\n  \"password\" : \"rawPassword123!\"\r\n}"
                },
                "auth-controller-test/invalid-password-log-in" : {
                  "value" : "{\r\n  \"email\" : \"test@mail.com\",\r\n  \"password\" : \"rawPassword123!\"\r\n}"
                },
                "auth-controller-test/success-log-in" : {
                  "value" : "{\r\n  \"email\" : \"test@mail.com\",\r\n  \"password\" : \"rawPassword123!\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "auth-controller-test/invalid-email-log-in" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"유저가 존재하지 않습니다.\"\r\n}"
                  }
                }
              }
            }
          },
          "401" : {
            "description" : "401",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "auth-controller-test/invalid-password-log-in" : {
                    "value" : "{\r\n  \"httpStatus\" : \"UNAUTHORIZED\",\r\n  \"errorMessage\" : \"잘못된 아이디 또는 비밀번호입니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "auth-controller-test/success-log-in" : {
                    "value" : "{\r\n  \"message\" : \"로그인이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"accessToken\" : \"accessToken\",\r\n    \"expiresAt\" : 1741341143\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "auth-controller-test/success-log-out" : {
                    "value" : "{\r\n  \"message\" : \"로그아웃이 완료되었습니다.\",\r\n  \"data\" : 1\r\n}"
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
          "403" : {
            "description" : "403",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "auth-controller-test/mismatch-info-fail-refresh-token" : {
                    "value" : "{\r\n  \"httpStatus\" : \"FORBIDDEN\",\r\n  \"errorMessage\" : \"유효하지 않은 토큰입니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "auth-controller-test/success-refresh-token" : {
                    "value" : "{\r\n  \"message\" : \"토큰 발급이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"accessToken\" : \"accessToken\",\r\n    \"expiresAt\" : 1741341143\r\n  }\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "auth-controller-test/duplicated-email-sign-up" : {
                  "value" : "{\r\n  \"email\" : \"test@mail.com\",\r\n  \"password\" : \"rawPassword123!\",\r\n  \"nickName\" : \"nickname\"\r\n}"
                },
                "auth-controller-test/success-sign-up" : {
                  "value" : "{\r\n  \"email\" : \"test@mail.com\",\r\n  \"password\" : \"rawPassword123!\",\r\n  \"nickName\" : \"nickname\"\r\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "409" : {
            "description" : "409",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "auth-controller-test/duplicated-email-sign-up" : {
                    "value" : "{\r\n  \"httpStatus\" : \"CONFLICT\",\r\n  \"errorMessage\" : \"이미 사용중인 이메일 입니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "auth-controller-test/success-sign-up" : {
                    "value" : "{\r\n  \"message\" : \"회원가입이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"id\" : 1,\r\n    \"email\" : \"test@mail.com\",\r\n    \"nickname\" : \"nickname\"\r\n  }\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "cart-controller-test/success-add-item" : {
                  "value" : "{\r\n  \"productId\" : 1,\r\n  \"quantity\" : 3\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "cart-controller-test/success-add-item" : {
                    "value" : "{\r\n  \"message\" : \"장바구니에 상품 추가가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"cartItemId\" : 1,\r\n    \"product\" : {\r\n      \"productId\" : 1,\r\n      \"name\" : \"보드카\",\r\n      \"description\" : \"독한 술입니다.\",\r\n      \"price\" : 120000,\r\n      \"stockQuantity\" : 50\r\n    },\r\n    \"quantity\" : 3\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-empty-user-coupons" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"userCoupons\" : [ ],\r\n    \"currentPage\" : 0,\r\n    \"totalPages\" : 1,\r\n    \"hasNext\" : false\r\n  }\r\n}"
                  },
                  "coupon-controller-test/success-get-user-coupons" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 목록 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"userCoupons\" : [ {\r\n      \"userCouponId\" : 1,\r\n      \"userId\" : 1,\r\n      \"coupon\" : {\r\n        \"id\" : 1,\r\n        \"couponName\" : \"1000원 할인쿠폰\",\r\n        \"discountValue\" : 1000\r\n      },\r\n      \"status\" : \"ISSUED\"\r\n    } ],\r\n    \"currentPage\" : 0,\r\n    \"totalPages\" : 1,\r\n    \"hasNext\" : false\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "coupon-controller-test/invalid-coupon-id-get-coupon" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-coupon" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"id\" : 1,\r\n    \"couponName\" : \"1000원 할인쿠폰\",\r\n    \"discountValue\" : 1000,\r\n    \"startTime\" : \"2025-03-01T00:00\",\r\n    \"endTime\" : \"2025-04-01T00:00\"\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "favorite-controller-test/invalid-id-delete-favorite" : {
                    "value" : "{\r\n  \"httpStatus\" : \"FORBIDDEN\",\r\n  \"errorMessage\" : \"접근 권한이 없습니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-delete-favorite" : {
                    "value" : "{\r\n  \"message\" : \"즐겨찾기 해제가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"cocktailId\" : 1,\r\n    \"name\" : \"블랙 러시안\",\r\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\r\n    \"type\" : \"CUSTOM\",\r\n    \"favoritedAt\" : \"2025-03-10T01:01:00\"\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/orders/1" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "order-controller-test/success-get-order",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "order-controller-test/success-get-order" : {
                    "value" : "{\r\n  \"message\" : \"주문 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"orderId\" : 1,\r\n    \"orderNumber\" : \"23031114553289\",\r\n    \"amount\" : 120000,\r\n    \"orderItems\" : [ {\r\n      \"productName\" : \"보드카\",\r\n      \"quantity\" : 3,\r\n      \"price\" : 120000\r\n    } ]\r\n  }\r\n}"
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
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "order-controller-test/success-delete-order" : {
                    "value" : "{\r\n  \"message\" : \"주문이 취소되었습니다.\",\r\n  \"data\" : null\r\n}"
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
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "order-controller-test/success-update-order" : {
                  "value" : "{\r\n  \"orderStatus\" : \"PROCESSING\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "order-controller-test/success-update-order" : {
                    "value" : "{\r\n  \"message\" : \"주문 상태가 변경되었습니다.\",\r\n  \"data\" : {\r\n    \"orderId\" : 1,\r\n    \"name\" : \"보드카 외 2개\",\r\n    \"amount\" : 120000,\r\n    \"status\" : \"PENDING_PAYMENT\",\r\n    \"updateDate\" : \"2025-03-10T16:08:17.783333\"\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/payments/1" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "payment-controller-test/success-find-payment",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "payment-controller-test/success-find-payment" : {
                    "value" : "{\r\n  \"message\" : \"결제 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"paymentKey\" : \"some-payment-key\",\r\n    \"status\" : \"DONE\",\r\n    \"orderId\" : \"23031114553289\",\r\n    \"orderName\" : \"보드카 외 2개\",\r\n    \"totalAmount\" : 120000\r\n  }\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "payment-controller-test/success-confirm-payment" : {
                  "value" : "{\r\n  \"orderId\" : \"보드카 외 2개\",\r\n  \"amount\" : 120000,\r\n  \"paymentKey\" : \"some-payment-key\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "payment-controller-test/success-confirm-payment" : {
                    "value" : "{\r\n  \"message\" : \"결제가 정상적으로 처리되었습니다.\",\r\n  \"data\" : {\r\n    \"paymentKey\" : \"some-payment-key\",\r\n    \"status\" : \"DONE\",\r\n    \"orderId\" : \"23031114553289\",\r\n    \"orderName\" : \"보드카 외 2개\",\r\n    \"totalAmount\" : 120000\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/products/1" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "product-controller-test/success-get-product",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "product-controller-test/success-get-product" : {
                    "value" : "{\r\n  \"message\" : \"상품 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"productId\" : 1,\r\n    \"name\" : \"보드카\",\r\n    \"description\" : \"독한 술입니다.\",\r\n    \"price\" : 120000,\r\n    \"stockQuantity\" : 50\r\n  }\r\n}"
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
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "product-controller-test/success-delete-product" : {
                    "value" : "{\r\n  \"message\" : \"상품 삭제가 완료되었습니다.\",\r\n  \"data\" : null\r\n}"
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
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "product-controller-test/success-update-product" : {
                  "value" : "{\r\n  \"name\" : \"보드카\",\r\n  \"description\" : \"독한 술입니다.\",\r\n  \"category\" : \"ALCOHOL\",\r\n  \"price\" : 120000,\r\n  \"stockQuantity\" : 50,\r\n  \"status\" : \"SALE\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "product-controller-test/success-update-product" : {
                    "value" : "{\r\n  \"message\" : \"상품 정보 수정이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"productId\" : 1,\r\n    \"name\" : \"보드카\",\r\n    \"description\" : \"독한 술입니다.\",\r\n    \"price\" : 120000,\r\n    \"stockQuantity\" : 50\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/admin/chats/2" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "chat-controller-test/success-find-chat-for-admin",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "chat-controller-test/success-find-chat-for-admin" : {
                    "value" : "{\r\n  \"messageList\" : [ {\r\n    \"sender\" : \"user\",\r\n    \"content\" : \"메시지 입니다.\",\r\n    \"timeMillis\" : 1741314450785\r\n  } ]\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/invalid-coupon-id-delete-coupon" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-delete-coupon" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 삭제가 완료되었습니다.\",\r\n  \"data\" : 1\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "admin-coupon-controller-test/invalid-coupon-id-update-coupon" : {
                  "value" : "{\r\n  \"couponName\" : \"1000원 할인쿠폰\",\r\n  \"discountValue\" : 1000,\r\n  \"startDate\" : \"2025-03-01\",\r\n  \"startTime\" : \"00:00\",\r\n  \"endDate\" : \"2025-04-01\",\r\n  \"endTime\" : \"00:00\"\r\n}"
                },
                "admin-coupon-controller-test/success-update-coupon" : {
                  "value" : "{\r\n  \"couponName\" : \"1000원 할인쿠폰\",\r\n  \"discountValue\" : 1000,\r\n  \"startDate\" : \"2025-03-01\",\r\n  \"startTime\" : \"00:00\",\r\n  \"endDate\" : \"2025-04-01\",\r\n  \"endTime\" : \"00:00\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/invalid-coupon-id-update-coupon" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-update-coupon" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 수정이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"id\" : 1,\r\n    \"couponName\" : \"1000원 할인쿠폰\",\r\n    \"discountValue\" : 1000,\r\n    \"startTime\" : \"2025-03-01T00:00\",\r\n    \"endTime\" : \"2025-04-01T00:00\"\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/carts/items/1" : {
      "delete" : {
        "tags" : [ "api" ],
        "operationId" : "cart-controller-test/success-remove-item",
        "responses" : {
          "204" : {
            "description" : "204",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "cart-controller-test/success-remove-item" : {
                    "value" : "{\r\n  \"message\" : \"장바구니에서 상품 제거가 완료되었습니다.\",\r\n  \"data\" : null\r\n}"
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
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "cart-controller-test/success-update-item-quantity" : {
                  "value" : "{\r\n  \"quantity\" : 2\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "cart-controller-test/success-update-item-quantity" : {
                    "value" : "{\r\n  \"message\" : \"장바구니에서 상품 수량 변경이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"cartItemId\" : 1,\r\n    \"product\" : {\r\n      \"productId\" : 1,\r\n      \"name\" : \"보드카\",\r\n      \"description\" : \"독한 술입니다.\",\r\n      \"price\" : 120000,\r\n      \"stockQuantity\" : 50\r\n    },\r\n    \"quantity\" : 3\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-check-favorite" : {
                    "value" : "{\r\n  \"message\" : \"즐겨찾기 상태 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"favorited\" : true\r\n  }\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "favorite-controller-test/duplicated-favorite-create-favorite" : {
                    "value" : "{\r\n  \"httpStatus\" : \"CONFLICT\",\r\n  \"errorMessage\" : \"이미 즐겨찾기한 레시피입니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "favorite-controller-test/invalid-favorite-id-create-favorite" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"칵테일이 존재하지 않습니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "favorite-controller-test/success-create-favorite" : {
                    "value" : "{\r\n  \"message\" : \"즐겨찾기 등록이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"cocktailId\" : 1,\r\n    \"name\" : \"블랙 러시안\",\r\n    \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\r\n    \"type\" : \"CUSTOM\",\r\n    \"favoritedAt\" : \"2025-03-10T01:01:00\"\r\n  }\r\n}"
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
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "coupon-controller-test/invalid-user-coupon-id-get-user-coupon" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-get-user-coupon" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"userCouponId\" : 1,\r\n    \"userId\" : 1,\r\n    \"coupon\" : {\r\n      \"id\" : 1,\r\n      \"couponName\" : \"1000원 할인쿠폰\",\r\n      \"discountValue\" : 1000\r\n    },\r\n    \"status\" : \"ISSUED\",\r\n    \"usedTime\" : null\r\n  }\r\n}"
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
          "404" : {
            "description" : "404",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "coupon-controller-test/invalid-user-coupon-id-use-user-coupon" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "coupon-controller-test/success-use-user-coupon" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 사용이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"userCouponId\" : 1,\r\n    \"userId\" : 1,\r\n    \"coupon\" : {\r\n      \"id\" : 1,\r\n      \"couponName\" : \"1000원 할인쿠폰\",\r\n      \"discountValue\" : 1000\r\n    },\r\n    \"status\" : \"ISSUED\",\r\n    \"usedTime\" : null\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/recipes/1/reviews" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "recipe-review-controller-test/success-get-all-recipe-review",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "recipe-review-controller-test/success-get-all-recipe-review" : {
                    "value" : "{\r\n  \"message\" : \"레시피 리뷰 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"content\" : [ {\r\n      \"cocktail\" : {\r\n        \"id\" : 1,\r\n        \"name\" : \"블랙 러시안\",\r\n        \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\r\n        \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\r\n        \"type\" : \"CUSTOM\",\r\n        \"likeCounts\" : 100,\r\n        \"userId\" : 1\r\n      },\r\n      \"user\" : {\r\n        \"id\" : 1,\r\n        \"email\" : \"test@mail.com\",\r\n        \"nickName\" : \"nickname\",\r\n        \"userRole\" : \"USER\"\r\n      },\r\n      \"reviewId\" : 1,\r\n      \"star\" : 5,\r\n      \"content\" : \"맛있어요!\"\r\n    } ],\r\n    \"pageable\" : \"INSTANCE\",\r\n    \"totalPages\" : 1,\r\n    \"totalElements\" : 1,\r\n    \"last\" : true,\r\n    \"size\" : 1,\r\n    \"number\" : 0,\r\n    \"sort\" : {\r\n      \"empty\" : true,\r\n      \"sorted\" : false,\r\n      \"unsorted\" : true\r\n    },\r\n    \"first\" : true,\r\n    \"numberOfElements\" : 1,\r\n    \"empty\" : false\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "api" ],
        "operationId" : "recipe-review-controller-test/success-create-recipe-review",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "recipe-review-controller-test/success-create-recipe-review" : {
                  "value" : "{\r\n  \"star\" : 5,\r\n  \"content\" : \"맛있어요!\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "recipe-review-controller-test/success-create-recipe-review" : {
                    "value" : "{\r\n  \"message\" : \"레시피 리뷰 등록이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"cocktail\" : {\r\n      \"id\" : 1,\r\n      \"name\" : \"블랙 러시안\",\r\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\r\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\r\n      \"type\" : \"CUSTOM\",\r\n      \"likeCounts\" : 100,\r\n      \"userId\" : 1\r\n    },\r\n    \"user\" : {\r\n      \"id\" : 1,\r\n      \"email\" : \"test@mail.com\",\r\n      \"nickName\" : \"nickname\",\r\n      \"userRole\" : \"USER\"\r\n    },\r\n    \"reviewId\" : 1,\r\n    \"star\" : 5,\r\n    \"content\" : \"맛있어요!\"\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/recipes/reviews/1" : {
      "get" : {
        "tags" : [ "api" ],
        "operationId" : "recipe-review-controller-test/success-get-recipe-review",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "recipe-review-controller-test/success-get-recipe-review" : {
                    "value" : "{\r\n  \"message\" : \"레시피 리뷰 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"cocktail\" : {\r\n      \"id\" : 1,\r\n      \"name\" : \"블랙 러시안\",\r\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\r\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\r\n      \"type\" : \"CUSTOM\",\r\n      \"likeCounts\" : 100,\r\n      \"userId\" : 1\r\n    },\r\n    \"user\" : {\r\n      \"id\" : 1,\r\n      \"email\" : \"test@mail.com\",\r\n      \"nickName\" : \"nickname\",\r\n      \"userRole\" : \"USER\"\r\n    },\r\n    \"reviewId\" : 1,\r\n    \"star\" : 5,\r\n    \"content\" : \"맛있어요!\"\r\n  }\r\n}"
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
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "recipe-review-controller-test/success-delete-recipe-review" : {
                    "value" : "{\r\n  \"message\" : \"레시피 리뷰 삭제가 완료되었습니다.\",\r\n  \"data\" : null\r\n}"
                  }
                }
              }
            }
          }
        }
      },
      "patch" : {
        "tags" : [ "api" ],
        "operationId" : "recipe-review-controller-test/success-update-recipe-review",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "recipe-review-controller-test/success-update-recipe-review" : {
                  "value" : "{\r\n  \"star\" : 5,\r\n  \"content\" : \"맛있어요!\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "recipe-review-controller-test/success-update-recipe-review" : {
                    "value" : "{\r\n  \"message\" : \"레시피 리뷰 수정이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"cocktail\" : {\r\n      \"id\" : 1,\r\n      \"name\" : \"블랙 러시안\",\r\n      \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\r\n      \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\r\n      \"type\" : \"CUSTOM\",\r\n      \"likeCounts\" : 100,\r\n      \"userId\" : 1\r\n    },\r\n    \"user\" : {\r\n      \"id\" : 1,\r\n      \"email\" : \"test@mail.com\",\r\n      \"nickName\" : \"nickname\",\r\n      \"userRole\" : \"USER\"\r\n    },\r\n    \"reviewId\" : 1,\r\n    \"star\" : 5,\r\n    \"content\" : \"맛있어요!\"\r\n  }\r\n}"
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
        "tags" : [ "api" ],
        "operationId" : "recipe-review-controller-test/success-get-my-recipe-review",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "recipe-review-controller-test/success-get-my-recipe-review" : {
                    "value" : "{\r\n  \"message\" : \"레시피 리뷰 조회가 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"content\" : [ {\r\n      \"cocktail\" : {\r\n        \"id\" : 1,\r\n        \"name\" : \"블랙 러시안\",\r\n        \"description\" : \"보드카와 깔루아로 만드는 칵테일\",\r\n        \"recipe\" : \"1.칠링한 온더락 글라스에 재료들을 붓는다.\\n2.젓는다.\",\r\n        \"type\" : \"CUSTOM\",\r\n        \"likeCounts\" : 100,\r\n        \"userId\" : 1\r\n      },\r\n      \"user\" : {\r\n        \"id\" : 1,\r\n        \"email\" : \"test@mail.com\",\r\n        \"nickName\" : \"nickname\",\r\n        \"userRole\" : \"USER\"\r\n      },\r\n      \"reviewId\" : 1,\r\n      \"star\" : 5,\r\n      \"content\" : \"맛있어요!\"\r\n    } ],\r\n    \"pageable\" : \"INSTANCE\",\r\n    \"totalPages\" : 1,\r\n    \"totalElements\" : 1,\r\n    \"last\" : true,\r\n    \"size\" : 1,\r\n    \"number\" : 0,\r\n    \"sort\" : {\r\n      \"empty\" : true,\r\n      \"sorted\" : false,\r\n      \"unsorted\" : true\r\n    },\r\n    \"first\" : true,\r\n    \"numberOfElements\" : 1,\r\n    \"empty\" : false\r\n  }\r\n}"
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "admin-coupon-controller-test/invalid-coupon-id-grant-user-coupon" : {
                  "value" : "{\r\n  \"userId\" : 1\r\n}"
                },
                "admin-coupon-controller-test/success-grant-user-coupon" : {
                  "value" : "{\r\n  \"userId\" : 1\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/invalid-coupon-id-grant-user-coupon" : {
                    "value" : "{\r\n  \"httpStatus\" : \"NOT_FOUND\",\r\n  \"errorMessage\" : \"쿠폰이 존재하지 않습니다\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "admin-coupon-controller-test/success-grant-user-coupon" : {
                    "value" : "{\r\n  \"message\" : \"쿠폰 지급이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"userCouponId\" : 1,\r\n    \"userId\" : 1,\r\n    \"coupon\" : {\r\n      \"id\" : 1,\r\n      \"couponName\" : \"1000원 할인쿠폰\",\r\n      \"discountValue\" : 1000\r\n    },\r\n    \"status\" : \"ISSUED\"\r\n  }\r\n}"
                  }
                }
              }
            }
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
                "$ref" : "#/components/schemas/api-orders-1486549215"
              },
              "examples" : {
                "super-admin-user-controller-test/duplicated-user-role-update-user-role" : {
                  "value" : "{\r\n  \"userRole\" : \"ADMIN\"\r\n}"
                },
                "super-admin-user-controller-test/success-update-user-role" : {
                  "value" : "{\r\n  \"userRole\" : \"ADMIN\"\r\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "409" : {
            "description" : "409",
            "content" : {
              "application/json;charset=UTF-8" : {
                "schema" : {
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "super-admin-user-controller-test/duplicated-user-role-update-user-role" : {
                    "value" : "{\r\n  \"httpStatus\" : \"CONFLICT\",\r\n  \"errorMessage\" : \"동일한 권한입니다.\"\r\n}"
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
                  "$ref" : "#/components/schemas/api-orders-1486549215"
                },
                "examples" : {
                  "super-admin-user-controller-test/success-update-user-role" : {
                    "value" : "{\r\n  \"message\" : \"권한 변경이 완료되었습니다.\",\r\n  \"data\" : {\r\n    \"userId\" : 1,\r\n    \"userRole\" : \"ADMIN\"\r\n  }\r\n}"
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
      "api-orders-1486549215" : {
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