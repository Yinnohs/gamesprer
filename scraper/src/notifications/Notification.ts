export type NotificationType = "success" | "error";

export class Notification {
    public readonly title: string;
    public readonly message: string;
    public readonly type: NotificationType;
    public readonly userId: string;
    public readonly gameToFindTitle: string;

    constructor(
        title: string,
        message: string,
        type: NotificationType,
        userId: string,
        gameToFindTitle: string
    ) {
        this.title = title;
        this.message = message;
        this.type = type;
        this.userId = userId;
        this.gameToFindTitle = gameToFindTitle;
    }
}