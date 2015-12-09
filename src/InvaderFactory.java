public class InvaderFactory {
    public static Invader createInvader(InvaderType model) {
        switch (model) {
            case SMALL:
                return new SmallInvader();
            case MEDIUM:
                return new MediumInvader();
            case LARGE:
                return new LargeInvader();
            default:
                return new NullInvader();
        }
    }
}
