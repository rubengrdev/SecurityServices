package securityservices.core.components.shared.operations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import securityservices.core.components.shared.stakeholders.StakeHolder;

public abstract class Operation {

    protected StakeHolder interested;
    protected String code, status, comments;
    protected double value, surcharges;
    protected LocalDateTime initDate, finishDate;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'-'HH:mm:ss");

    public Operation() {
    }

    public Operation(String code, StakeHolder interested, double value, double surcharges,
            String status, String comments, String initDate, String finishDate) {
        this.code = code;
        this.interested = interested;
        this.value = value;
        this.surcharges = surcharges;
        this.status = status;
        this.comments = comments;
        this.setBeginDate(initDate);
        this.setFinishDate(finishDate);
    }

    public String getCode() {
        return code;
    }

    public boolean setCode(String code) {
        if (code != null && code.length() >= 2) {
            this.code = code;
            return true;
        }
        return false;

    }

    public String getInterested() {
        return interested.getCode();
    }

    public boolean setInterested(StakeHolder interested) {
        if (interested == null) {
            return false;
        }
        this.interested = interested;
        return true;
    }

    public double getValue() {
        return value;
    }

    public boolean setValue(double value) {
        if (value < 0) {
            return false;
        }
        this.value = value;
        return true;
    }

    public double getSurcharges() {
        return surcharges;
    }

    public boolean setSurcharges(double surcharges) {
        if (surcharges < 0) {
            return false;
        }
        this.surcharges = surcharges;
        return true;
    }

    public String getStatus() {
        return status;
    }

    public boolean setStatus(String status) {
        if (status == null) {
            return false;
        }
        this.status = status;
        return true;
    }

    public String getComments() {
        return comments;
    }

    public boolean setComments(String comments) {
        if (comments == null || comments.length() < 4) {
            return false;
        }
        this.comments = comments;
        return true;
    }

    public String getBeginDate() {
        if (this.initDate != null) {
            return this.initDate.format(this.dateTimeFormatter);
        }
        return "";
    }

    public boolean setBeginDate(String beginDate) throws DateTimeParseException {
        if (beginDate != null && beginDate.trim().length() > 0) {
            this.initDate = LocalDateTime.parse(beginDate, this.dateTimeFormatter);
            return true;
        }
        return false;
    }

    public String getFinishDate() {
        if (this.finishDate != null) {
            return this.finishDate.format(this.dateTimeFormatter);
        }
        return "";
    }

    public boolean setFinishDate(String finishDate) throws DateTimeParseException {
        if (finishDate != null && finishDate.trim().length() > 0) {
            this.finishDate = LocalDateTime.parse(finishDate, this.dateTimeFormatter);
            return true;
        }
        return false;
    }
}
