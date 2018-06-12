package nl.han.oose.dea.jamielvanengen.constants;

public enum HttpResponse {
    OK(200),
    SUCCESFUL_POST(201),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404);

    private int value;

    HttpResponse(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
