package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class CustomButton extends JButton {
    private boolean isSelected;
    private FrmPnlGhe parentFrame;
    public CustomButton(String text, FrmPnlGhe parentFrame) {
        super(text);
        this.parentFrame = parentFrame;
        isSelected = false;
        setBackground(Color.GRAY);
        setOpaque(true);
        setBorderPainted(false);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isSelected = !isSelected;
                if (isSelected) {
                    setBackground(Color.GREEN);
                } else {
                    setBackground(Color.GRAY);
                }
                parentFrame.updateTextField();
            }
        });
        
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

public class FrmPnlGhe extends JFrame {
    private JPanel panel;
    private ArrayList<CustomButton> selectedButtons;
    private JTextField txt;
    private int selectedSeatsCount = 0;
    public FrmPnlGhe() {
        setTitle("Danh sách ghế");
        setLayout(new BorderLayout());
        selectedButtons = new ArrayList<>();
        txt = new JTextField();
        panel = new JPanel(new GridLayout(11, 11));
        for (char c = 'A'; c <= 'K'; c++) {
            for (int i = 1; i <= 10; i++) {
                CustomButton button = new CustomButton("" + c + i, this);
                panel.add(button);
            }
        }

        add(panel, BorderLayout.CENTER);
        add(txt, BorderLayout.SOUTH);
        setSize(700, 500);
        setLocationRelativeTo(null);
    }
//    public void setTotalPrice(String price) {
//        txtTotalPrice.setText(price);
//    }
    public void updateTextField() {
        StringBuilder resultString = new StringBuilder();
        selectedButtons.clear();
        for (Component comp : panel.getComponents()) {
            if (comp instanceof CustomButton) {
                CustomButton button = (CustomButton) comp;
                if (button.isSelected()) {
                    selectedButtons.add(button);
                    resultString.append(button.getText() + ",");
                }
            }
        }
        txt.setText(resultString.toString());
        updateSelectedSeatsCount();
    }
    public void updateSelectedSeatsCount() {
        selectedSeatsCount = 0;
        for (CustomButton button : selectedButtons) {
            if (button.isSelected()) {
                selectedSeatsCount++;
            }
        }
    }
    public int getSelectedSeatsCount() {
        return selectedSeatsCount;
    }
    // Phương thức truy cập để lấy nội dung của JTextField
    public String getTextFieldContent() {
        return txt.getText();
    }

    public static void main(String[] args) {
        FrmPnlGhe frame = new FrmPnlGhe();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
