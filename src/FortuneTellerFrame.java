import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class FortuneTellerFrame extends JFrame
{
    JPanel mainPanel;

    JPanel topPanel;
    JLabel fortuneTellerlabel;
    ImageIcon fortuneTellerImage;

    JPanel midPanel;
    JTextArea textArea;
    JScrollPane scrollPane;

    JPanel botPanel;
    JButton newFortuneButton;
    JButton quitButton;

    public FortuneTellerFrame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);

        createMidPanel();
        mainPanel.add(midPanel, BorderLayout.CENTER);

        createBottomPanel();
        mainPanel.add(botPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(1500,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel()
    {
        topPanel = new JPanel();
        fortuneTellerImage = new ImageIcon(("C://Users//maxim//Desktop//COMP 2 Work//FortuneTeller//src//FortuneTeller.png"));
        fortuneTellerlabel = new JLabel("Max's Fortune Teller",fortuneTellerImage, JLabel.CENTER);
        fortuneTellerlabel.setFont(new java.awt.Font("Times New Roman", 0, 35));
        fortuneTellerlabel.setVerticalTextPosition(JLabel.BOTTOM);
        fortuneTellerlabel.setHorizontalTextPosition(JLabel.CENTER);
        topPanel.add(fortuneTellerlabel);
    }

    private void createMidPanel()
    {
        midPanel = new JPanel();
        textArea = new JTextArea(6,40);
        textArea.setFont(new java.awt.Font("Tahoma", 0, 25));
        textArea.setForeground(Color.white);
        textArea.setBackground(Color.blue);
        scrollPane = new JScrollPane(textArea);


        DefaultCaret caret = (DefaultCaret)textArea.getCaret(); //stop auto scroll so new fortunes can be added at the top
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        midPanel.add(scrollPane);
    }

    private void createBottomPanel()
    {
        botPanel = new JPanel();
        botPanel.setLayout(new GridLayout(1,2));

        quitButton = new JButton("Quit");
        newFortuneButton = new JButton("Read My Fortune!");

        quitButton.setFont(new java.awt.Font("Serif", 0, 35));
        newFortuneButton.setFont(new java.awt.Font("Serif", 0, 35));

        botPanel.add(quitButton);
        botPanel.add(newFortuneButton);

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));
        newFortuneButton.addActionListener((ActionEvent ae) -> newFortune());
    }
    public int pastFortune = 0;
    public int randomFort = 0;
    private void newFortune()
    {
        String fortunes[] = {"A fresh start will put you on your way.", "Advice, when most needed, is least heeded.", "A pleasant surprise is waiting for you.",
                "All your hard work will soon pay off.", "Any decision you have to make tomorrow is a good decision.", "Courtesy is contagious.",
                "Emulate what you respect in your friends.", "It is better to deal with problems before they arise.", "It is worth reviewing some old lessons.",
                "The harder you work, the luckier you get.", "The sure way to predict the future is to invent it.", "Your hard work will payoff today."
        };
        while(pastFortune == randomFort) //if randomFortune = pastFortune, reroll random until different
        {
            randomFort = ThreadLocalRandom.current().nextInt(0, 12);
        }
        textArea.setText(fortunes[randomFort]+ "\n" + textArea.getText());
        pastFortune = randomFort; //set pastfortune = current fortune
    }
}
