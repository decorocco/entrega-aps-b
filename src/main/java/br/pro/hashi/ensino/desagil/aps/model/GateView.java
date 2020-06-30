package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {
    private final static int height = 200;
    private final static int width = 300;
    private final JCheckBox inputCheck;
    private final JCheckBox inputCheck2;
    private final JCheckBox inputCheck3;
    private final Switch switch1;
    private final Switch switch2;
    private final Switch switch3;
    private final Image image;
    private final Light light;
    private Color color;

    public GateView(Gate gates) {
        super(width, height);

        this.light = new Light(255, 0, 0);

        inputCheck = new JCheckBox();
        inputCheck2 = new JCheckBox();
        inputCheck3 = new JCheckBox();
        switch1 = new Switch();
        switch2 = new Switch();
        switch3 = new Switch();

        setSize(20, 20);
        if (gates.getInputSize() == 2) {
            add(inputCheck, width / 2 - 42, 40, 20, 20);
            add(inputCheck2, width / 2 + 20, 40, 20, 20);
        } else if (gates.getInputSize() > 2) {
            add(inputCheck, width / 2 - 70, 40, 20, 20);
            add(inputCheck2, width / 2 - 10, 40, 20, 20);
            add(inputCheck3, width / 2 + 50, 40, 20, 20);
        } else {
            add(inputCheck, width / 2 - 10, 40, 20, 20);
        }

        gates.connect(0, switch1);
        if (gates.getInputSize() == 2) {
            gates.connect(1, switch2);
        } else if (gates.getInputSize() > 2) {
            gates.connect(1, switch2);
            gates.connect(2, switch3);
        }

        this.light.connect(0, gates);

        color = light.getColor();

        inputCheck.addActionListener(this);
        if (gates.getInputSize() == 2) {
            inputCheck2.addActionListener(this);
        } else if (gates.getInputSize() > 2) {
            inputCheck2.addActionListener(this);
            inputCheck3.addActionListener(this);
        }

        String name = gates.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        addMouseListener(this);

        update();
    }

    private void update() {
        if (inputCheck.isSelected() && inputCheck2.isSelected() && inputCheck3.isSelected()) {
            switch1.turnOn();
            switch2.turnOn();
            switch3.turnOn();

        } else if (inputCheck.isSelected() && inputCheck2.isSelected()) {
            switch1.turnOn();
            switch2.turnOn();
            switch3.turnOff();
        } else if (inputCheck.isSelected() && inputCheck3.isSelected()) {
            switch1.turnOn();
            switch2.turnOff();
            switch3.turnOn();
        } else if (inputCheck2.isSelected() && inputCheck3.isSelected()) {
            switch1.turnOff();
            switch2.turnOn();
            switch3.turnOn();
        } else if (inputCheck.isSelected()) {
            switch1.turnOn();
            switch2.turnOff();
            switch3.turnOff();
        } else if (inputCheck2.isSelected()) {
            switch1.turnOff();
            switch2.turnOn();
            switch3.turnOff();
        } else if (inputCheck3.isSelected()) {
            switch1.turnOff();
            switch2.turnOff();
            switch3.turnOn();
        } else {
            switch1.turnOff();
            switch2.turnOff();
            switch3.turnOff();
        }
        color = light.getColor();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        if (x >= height / 2 - 30 && x <= height + 10 && y >= 130 && y <= 170 && Math.abs((x - width / 2 - 10)) * Math.abs((x - width / 2 - 10)) + Math.abs((y - 150)) * Math.abs((y - 150)) < 200) {
            light.setColor(JColorChooser.showDialog(this, null, color));
        }
        update();
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void paintComponent(Graphics g) {
        int img_height = 100;
        int img_width = 250;

        super.paintComponent(g);

        g.drawImage(image, (width / 2) - img_width / 2, (height / 2) - img_height / 2, img_width, img_height, this);

        g.setColor(color);
        g.fillOval(width / 2 - 10, 150, 20, 20);

        getToolkit().sync();
    }
}
