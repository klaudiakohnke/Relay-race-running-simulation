package com.pl.edu.wat;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MainPanel {
    private JButton buttonOK;
    private JSpinner numberOfTeamsSpinner;
    private JSpinner numberOfPlayersSpinner;
    private JPanel mainFrame;

    public MainPanel()
    {
        mainFrame = new JPanel(new GridBagLayout());
        mainFrame.setSize(400, 300);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        /*
        * Text and button - GridBagConstraints. Table: 3 x 2 = X x Y
        * Order: 2,3,4,1 line
        */

        createWindowControls(mainFrame, c);
        createsWaitingRoomControls(mainFrame, c);
        createOkButton(mainFrame, c);
        createWelcomeText(mainFrame, c);
    }

    private void createWelcomeText(JPanel frame, GridBagConstraints c)
    {
        JLabel welcomeText = new JLabel();
        welcomeText.setText("Symulator - Stadion");
        welcomeText.setFont(new Font("Serif", Font.PLAIN, 30));
        c.gridx = 0;
        c.gridy = 0;
        frame.add(welcomeText, c);
    }

    private void createWindowControls(JPanel frame, GridBagConstraints c)
    {
        JLabel label = new JLabel();
        label.setText("Number of teams:");
        label.setFont(new Font("Serif", Font.PLAIN, 22));
        c.gridx = 0;
        c.gridy = 1;
        frame.add(label, c);
        c.gridx = 1;
        numberOfTeamsSpinner = new JSpinner(new SpinnerNumberModel(5.0, 2.0, 8.0, 1.0));
        frame.add(numberOfTeamsSpinner, c);
    }

    private void createsWaitingRoomControls(JPanel frame, GridBagConstraints c)
    {
        JLabel label = new JLabel();
        label.setText("Number of players: ");
        label.setFont(new Font("Serif", Font.PLAIN, 22));
        c.gridx = 0;
        c.gridy = 2;
        frame.add(label, c);

        c.gridx = 1;
        numberOfPlayersSpinner = new JSpinner(new SpinnerNumberModel(5.0, 1.0, 16.0, 1.0));
        frame.add(numberOfPlayersSpinner, c);
    }

    private void createOkButton(JPanel frame, GridBagConstraints c)
    {
        buttonOK = new JButton();
        buttonOK.setText("Start");
        c.weighty = 2;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        frame.add(buttonOK, c);
    }

    public int getNumberOfTeams()
    {
        return ((Double) numberOfTeamsSpinner.getValue()).intValue();
    }

    public int getNumberOfPlayers()
    {
        return ((Double) numberOfPlayersSpinner.getValue()).intValue();
    }

    public JPanel getPanel()
    {
        return mainFrame;
    }

    public JButton getButton()
    {
        return buttonOK;
    }

}
