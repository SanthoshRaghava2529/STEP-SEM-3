public final class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {

        this.memberId = memberId;

        this.bookIds = new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {
            this.bookIds[i] = bookIds[i];
        }
    }

    public String[] getBookIds() {

        String[] copy = new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {
            copy[i] = bookIds[i];
        }

        return copy;
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        String[] newBookIds = getBookIds();

        if (index >= 0 && index < newBookIds.length) {
            newBookIds[index] = newId;
        }

        return new LoanReceipt(memberId, newBookIds);
    }

    public String getMemberId() {
        return memberId;
    }
}
