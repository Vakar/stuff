package space.vakar.stuff.captcha.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"success", "challenge_ts", "hostname", "error-codes"})
public class GoogleResponse {
  @JsonProperty("success")
  private boolean success;

  @JsonProperty("challenge_ts")
  private String challengeTs;

  @JsonProperty("hostname")
  private String hostname;

  @JsonProperty("error-codes")
  private ErrorCode[] errorCodes;

  @JsonIgnore
  public boolean hasClientError() {
    ErrorCode[] errors = getErrorCodes();
    if (errors == null) {
      return false;
    }
    for (ErrorCode error : errors) {
      if (error == ErrorCode.INVALID_RESPONSE || error == ErrorCode.MISSING_RESPONSE) {
        return true;
      }
    }
    return false;
  }

  enum ErrorCode {
    MISSING_SECRET,
    INVALID_SECRET,
    MISSING_RESPONSE,
    INVALID_RESPONSE;

    private static Map<String, ErrorCode> errorsMap = new HashMap<>(4);

    static {
      errorsMap.put("missing-input-secret", MISSING_SECRET);
      errorsMap.put("invalid-input-secret", INVALID_SECRET);
      errorsMap.put("missing-input-response", MISSING_RESPONSE);
      errorsMap.put("invalid-input-response", INVALID_RESPONSE);
    }

    @JsonCreator
    public static ErrorCode forValue(String value) {
      return errorsMap.get(value.toLowerCase());
    }
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getChallengeTs() {
    return challengeTs;
  }

  public void setChallengeTs(String challengeTs) {
    this.challengeTs = challengeTs;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public ErrorCode[] getErrorCodes() {
    return errorCodes;
  }

  public void setErrorCodes(ErrorCode[] errorCodes) {
    this.errorCodes = errorCodes;
  }
}
