import javax.swing.*;

public class Main {

    static MainWindow window = new MainWindow();
    static FrameContents contents = new FrameContents();

    public static void main(String[] args) {

        window.setVisible(true);
        window.add(contents);
    }
}
