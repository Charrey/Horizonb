package gui.components;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import model.ServerInfo;
import utils.ColorUtils;


public class ServerView extends GridPane {

    Label name;
    Label local;
    Label status;


    public ServerView(ServerInfo info, boolean status) {
        this.setStyle("-fx-background-color: " + ColorUtils.RandomColor() + ";");
        name = new Label("\"" + info.getName() + "\"");
        local = new Label(info.getIP().equals("127.0.0.1") ? "local" : "");
        local.setAlignment(Pos.CENTER);
        this.status = new Label(status ? "ONLINE" : "OFFLINE");

        this.add(name, 0, 0);
        this.add(local, 1, 0);
        this.add(this.status, 2, 0);

        getColumnConstraints().add(new ColumnConstraints(400/3));
        getColumnConstraints().add(new ColumnConstraints(400/3));
        getColumnConstraints().add(new ColumnConstraints(400/3));

        this.setPadding(new Insets(10, 20, 10, 20));
        this.prefHeight(40);
    }
}
