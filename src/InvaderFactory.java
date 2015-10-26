public class InvaderFactory {
    public static Invader createInvader(InvaderType model) {
        Invader invader = null;
        switch (model) {
            case SMALL:
                invader = new SmallInvader();
                break;

            case MEDIUM:
                invader = new MediumInvader();
                break;

            case LARGE:
                invader = new LargeInvader();
                break;

            default:
                // TODO: throw some exception
                break;
        }
        return invader;
    }
}
