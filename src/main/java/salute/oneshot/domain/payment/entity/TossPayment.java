package salute.oneshot.domain.payment.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossPayment {

    private final String mId;
    private final String version;
    private final String paymentKey;
    private final PaymentStatus status;
    private final String lastTransactionKey;
    private final String method;
    private final String orderId;
    private final String orderName;
    private final OffsetDateTime requestedAt;
    private final OffsetDateTime approvedAt;
    private final boolean useEscrow;
    private final boolean cultureExpense;
    private final Card card;
    private final VirtualAccount virtualAccount;
    private final Transfer transfer;
    private final MobilePhone mobilePhone;
    private final GiftCertificate giftCertificate;
    private final CashReceipt cashReceipt;
    private final CashReceipts cashReceipts;
    private final Discount discount;
    private final List<Cancel> cancels;
    private final String secret;
    private final String type;
    private final EasyPay easyPay;
    private final String country;
    private final Failure failure;
    private final boolean isPartialCancelable;
    private final Receipt receipt;
    private final Checkout checkout;
    private final String currency;
    private final Long totalAmount;
    private final Long balanceAmount;
    private final Long suppliedAmount;
    private final Long vat;
    private final Long taxFreeAmount;
    private final Map<String, String> metadata;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Card {
        private final String issuerCode;
        private final String acquirerCode;
        private final String number;
        private final int installmentPlanMonths;
        private final boolean isInterestFree;
        private final String interestPayer;
        private final String approveNo;
        private final boolean useCardPoint;
        private final String cardType;
        private final String ownerType;
        private final String acquireStatus;
        private final Long amount;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class VirtualAccount {
        private final String accountNumber;
        private final String bank;
        private final String customerName;
        private final OffsetDateTime dueDate;
        private final OffsetDateTime refundDueDate;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Transfer {
        private final String bank;
        private final String accountNumber;
        private final String senderName;
        private final OffsetDateTime transferredAt;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class MobilePhone {
        private final String carrier;
        private final String phoneNumber;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class GiftCertificate {
        private final String provider;
        private final Long amount;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class CashReceipt {
        private final String receiptType;
        private final String receiptNumber;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class CashReceipts {
        private final String receiptType;
        private final String receiptNumber;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Discount {
        private final Long amount;
        private final String type;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Cancel {
        private final String cancelKey;
        private final String cancelReason;
        private final OffsetDateTime cancelledAt;
        private final Long amount;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class EasyPay {
        private final String provider;
        private final String orderId;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Failure {
        private final String code;
        private final String message;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Receipt {
        private final String url;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Checkout {
        private final String url;
    }
}
