package Pre;

public class ProducerA {
    private String name;
    private String email;
    private int castCrewId;

    public ProducerA(String name, String email, int castCrewId) {
        this.name = name;
        this.email = email;
        this.castCrewId = castCrewId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getCastCrewId() {
        return castCrewId;
    }
}
