package pt.isel.ls.http;

public enum StatusCode {
    Ok(200),
    BadRequest(400),
    NotFound(404),
    NotImplemented(501),
    InternalServerError(500);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCodeValue() {
        return this.code;
    }
}
