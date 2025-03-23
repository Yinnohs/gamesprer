import { HttpClient } from "../http/HttpClient.js";
import { Notification } from "./Notification.js";

export class NotificationService {
    private client: HttpClient;

    constructor(client: HttpClient) {
        this.client = client;
    }

    public async sendNotificationReFindNotification(titleToFind: string, userId:string): Promise<void> {
        try {
            const title = 'Base de datos de juegos actualizada';
            const message = `Ya se han podido actualizar la base de datos de juegos referente a la busqueda: ${titleToFind}
            porfavor no haga nada el programa se encargará de ahcer la búsqueda.`;
            
            const notification = new Notification(
                title,
                message,
                'success',
                userId,
                titleToFind
            );
            console.log("Esta es la notificacion enviada: ");
            console.log(notification);
            const messageUrl = `http://localhost:5052/api/v1/scraper-notifications/notify/user`;
            await this.client.post<Notification, string>(notification, {}, messageUrl);
        } catch (error) {
            console.error('Error sending notification:', error);
            throw error;
        }
    }
}