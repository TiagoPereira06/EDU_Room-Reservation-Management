package pt.isel.ls.http;

public enum StatusCode {
    Ok(200),
    SeeOther(303),
    BadRequest(400),
    NotFound(404),
    Conflict(409),
    InternalServerError(500);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCodeValue() {
        return this.code;
    }
}
