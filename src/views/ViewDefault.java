package views;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;



@SuppressWarnings("serial")
public class ViewDefault {

    public enum TypeField{
        TITLES, TEXTFIELDS, DEFAULT, TOOLTIP, BUTTON_LOGIN;
    }

    public class Frames extends JFrame{

        public Frames(int width, int height) {
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension d = tk.getScreenSize();
            int posX = d.width/2 - width/2;
            int posY = d.height/2 - height/2;
            setBounds(posX, posY, width, height);
            setLayout(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setUndecorated(true);
            setBackground(new Color(0,0,0,0));
        }
    }

    public class Panels extends JPanel{

        Shape shape;

        public Panels(int posX, int posY, int width, int height) {
            setBounds(posX, posY, width, height);
            setLayout(null);
            shape = new RoundRectangle2D.Float(0F,0F, (float)(width),(float) (height),50F,50F);

            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    requestFocusPanel();
                }
            });
        }

        public void requestFocusPanel() {
            this.requestFocus();
        }

        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //Suavisa a linha aplicando anti-aliasing
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //Suavisa texto aplicando anti-aliasing
            g2d.setStroke(new BasicStroke(3.0F)); //Define expessura da linha
            g2d.setColor(Color.decode("#D9D9D9")); //Define cor novamente
            g2d.fill(shape); //Desenha o preenchimento do shape
        }

        void addButtonsHover(ButtonsHover[] buttonsHover) {
            for(int i = 0; i < buttonsHover.length; i++) {
                this.add(buttonsHover[i]);
            }
        }

        void addTextFields(TextFieldsBorder[] textFields) {
            for(int i = 0; i < textFields.length; i++) {
                this.add(textFields[i]);
            }
        }
    }

    public class Panels2 extends JPanel{

        Shape shape;

        public Panels2(int posX, int posY, int width, int height) {
            setBounds(posX, posY, width, height);
            setLayout(null);
            shape = new RoundRectangle2D.Float(2F,2F, (float)(width)-4,(float) (height)-4,50F,50F);

            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    requestFocusPanel();
                }
            });
        }

        public void requestFocusPanel() {
            this.requestFocus();
        }

        protected void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //Suavisa a linha aplicando anti-aliasing
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //Suavisa texto aplicando anti-aliasing
            g2d.setStroke(new BasicStroke(2F)); //Define expessura da linha
            g2d.setColor(Color.decode("#D9D9D9")); //Define cor novamente
            g2d.fill(shape); //Desenha o preenchimento do shape
            g2d.setColor(Color.black);
            g2d.draw(shape);
        }

        void addButtonsHover(ButtonsHover[] buttonsHover) {
            for(int i = 0; i < buttonsHover.length; i++) {
                this.add(buttonsHover[i]);
            }
        }

        void addTextFields(TextFieldsBorder[] textFields) {
            for(int i = 0; i < textFields.length; i++) {
                this.add(textFields[i]);
            }
        }
    }

    public class LogoLabel extends JLabel{

        public LogoLabel(String iconName, int posX, int posY, int width, int height) throws IOException {
            setBounds(posX, posY, width, height);
            setIcon(selectIcon(iconName, width, height));
        }
    }

    public class ButtonsHover extends JLabel{
        public ButtonsHover(String iconName, int posX, int posY, int width, int height) {
            setBounds(posX, posY, width, height);
            setIcon(selectIcon(iconName + "1", width, height));
            addMouseListener(new MouseAdapter() {
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    setIcon(selectIcon(iconName + "1", width, height));
                }

                public void mouseEntered(MouseEvent e) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    setIcon(selectIcon(iconName + "2", width, height));
                }

                public void mouseClicked(MouseEvent e) {
                    setIcon(selectIcon(iconName + "1", width, height));
                }
            });
        }
    }

    public class TextFieldsBorder extends JFormattedTextField {

        public String toolTip;
        public TypeField typeField;
        public int sizeFont;

        public TextFieldsBorder(String toolTip, int posX, int posY, int width, int height, TypeField typeField, int sizeFont, StringLimiter.TypeText typeText, int maxLength) {
            setBounds(posX, posY, width, height);
            this.toolTip = toolTip;
            this.typeField = typeField;
            this.sizeFont = sizeFont;
            setFont(selectFont(TypeField.TOOLTIP, sizeFont));
            setForeground(selectForeground(TypeField.TOOLTIP));
            setDocument(new StringLimiter(maxLength, typeText));
            setHorizontalAlignment(LEFT);
            setMargin(new Insets(0,5,-1,5));
            setOpaque(false); //Quando setado como Falso, � poss�vel utilizar transpar�ncia no Background

            addFocusListener(new FocusListener() {

                @Override
                public void focusLost(FocusEvent e) {
                    LoseFocusToolTip();
                }

                @Override
                public void focusGained(FocusEvent e) {
                    FocusToolTip();
                }
            });
        }

        public void FocusToolTip() {
            if(getText().equals(this.toolTip)) {
                setText("");
                setFont(selectFont(typeField, sizeFont));
                setForeground(selectForeground(typeField));
            }

        }
        public void LoseFocusToolTip() {
            if(getText().length() == 0) {
                setText(toolTip);
                setFont(selectFont(TypeField.TOOLTIP, sizeFont));
                setForeground(selectForeground(TypeField.TOOLTIP));
            }
        }

        protected void paintComponent(Graphics g) {
            //Seleciona cor, posi��o e pinta o Background
            g.setColor(Color.decode("#FFFBFB"));
            g.fillRoundRect(0, 0, getWidth()-1, getHeight(), 10, 10);
            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            //Seleciona cor, expessura, posi��o e pinta as bordas
	        /*Graphics2D g2d = (Graphics2D) g.create();
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //Suavisa a linha aplicando anti-aliasing
	        g2d.setStroke(new BasicStroke(0F)); //Define expessura da linha
	        g2d.setColor(Color.black); //Define cor
	       // g2d.draw(new RoundRectangle2D.Float(1F,1F, (float)(getWidth()-3),(float) (getHeight()-2),20F,20F));*/
        }

    }

    public class PassFieldsBorder extends JPasswordField {

        public String toolTip;
        public TypeField typeField;
        public int sizeFont;

        public PassFieldsBorder(String toolTip, int posX, int posY, int width, int height, TypeField typeField, int sizeFont) {
            setBounds(posX, posY, width, height);
            this.toolTip = toolTip;
            this.typeField = typeField;
            this.sizeFont = sizeFont;
            setFont(selectFont(TypeField.TOOLTIP, sizeFont));
            setForeground(selectForeground(TypeField.TOOLTIP));
            setHorizontalAlignment(LEFT);
            setMargin(new Insets(0,5,-1,5));
            setOpaque(false); //Quando setado como Falso, � poss�vel utilizar transpar�ncia no Background
            setEchoChar((char)0);
            addFocusListener(new FocusListener() {

                @Override
                public void focusLost(FocusEvent e) {
                    LoseFocusToolTip();
                }

                @Override
                public void focusGained(FocusEvent e) {
                    FocusToolTip();
                }
            });
        }

        public void FocusToolTip() {
            if(getText().equals(this.toolTip)) {
                setText("");
                setFont(selectFont(typeField, sizeFont));
                setForeground(selectForeground(typeField));
                setEchoChar('•');
            }

        }
        public void LoseFocusToolTip() {
            if(getText().length() == 0) {
                setText(toolTip);
                setFont(selectFont(TypeField.TOOLTIP, sizeFont));
                setForeground(selectForeground(TypeField.TOOLTIP));
                setEchoChar((char)0);
            }
        }

        protected void paintComponent(Graphics g) {
            //Seleciona cor, posi��o e pinta o Background
            g.setColor(Color.decode("#FFFBFB"));
            g.fillRoundRect(0, 0, getWidth()-1, getHeight(), 10, 10);
            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            //Seleciona cor, expessura, posi��o e pinta as bordas
	        /*Graphics2D g2d = (Graphics2D) g.create();
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //Suavisa a linha aplicando anti-aliasing
	        g2d.setStroke(new BasicStroke(1F)); //Define expessura da linha
	        g2d.setColor(Color.black); //Define cor
	       g2d.draw(new RoundRectangle2D.Float(0F,0F, (float)(getWidth()-2),(float) (getHeight()-1),20F,20F));*/
        }

    }

    protected Font selectFont(TypeField typeField, int sizeFont) {
        switch (typeField) {
            case TITLES:		return new Font("Arial", Font.BOLD | Font.ITALIC, sizeFont);
            case TEXTFIELDS:	return new Font("Arial", Font.BOLD, sizeFont);
            case TOOLTIP:		return new Font("Arial", Font.BOLD | Font.ITALIC, sizeFont);
            case BUTTON_LOGIN:	return new Font("Arial", Font.BOLD, sizeFont);
            default:			return new Font("Arial", Font.BOLD, sizeFont);
        }
    }

    protected Color selectForeground(TypeField typeField) {
        switch (typeField) {
            case TITLES: 		return Color.BLACK;
            case TEXTFIELDS:	return Color.DARK_GRAY;
            case TOOLTIP:		return new Color(0,0,0,50);
            default:			return Color.BLACK;
        }
    }

    private Color selectColor(int ID) {
        switch (ID) {
            case 1:	return Color.decode("#1B2B55");
            case 2:	return Color.decode("#3A91C3");
            default:			return Color.BLACK;
        }
    }

    private ImageIcon selectIcon(String iconName, int width, int height) {
        return new ImageIcon(new ImageIcon(new File("src/assets/images/" + iconName + ".png").getAbsolutePath()).getImage().getScaledInstance(width, height, 1));
    }

    public class StringLimiter extends PlainDocument {
        public enum TypeText{
            NOME, ENDERECO, NUMERO_INTEIRO, NUMERO_DECIMAL, DATA, EMAIL;
        }

        private int qtString;
        private TypeText typeText;

        public StringLimiter(int qtString, TypeText typeText) {
            this.qtString = qtString;
            this.typeText = typeText;
        }

        @Override
        public void insertString(int i, String string, AttributeSet as) throws BadLocationException {

            if(string == null || getLength() == qtString) {
                return;
            }

            int totalString = getLength() + string.length();

            String validChars = "";

            switch (typeText) {
                case NOME:			validChars = "[^\\p{IsLatin} ]";			break;
                case ENDERECO:		validChars = "[^\\p{IsLatin} ][^0-9]";		break;
                case NUMERO_INTEIRO:validChars = "[^0-9]";						break;
                case NUMERO_DECIMAL:validChars = "[^0-9.,]";					break;
                case DATA:			validChars = "[^0-9/]";						break;
                case EMAIL:			validChars = "[^\\p{IsLatin}][^@._\\-][^0-9]^[,]";	break;
            }

            string = string.replaceAll(validChars, "");

            if(totalString <= qtString) {
                super.insertString(i, string, as);
            }
            else {
                String newString = string.substring(0, qtString);
                super.insertString(i, newString, as);
            }
        }
    }


    public class LabelButton extends JLabel {

        public TypeField typeField;
        public int colors = 1;
        public String texts;

        public LabelButton(String text, int posX, int posY, int width, int height, TypeField typeField, int sizeFont) {
            this.typeField = typeField;
            this.texts = text;
            setText(this.texts);
            setBounds(posX, posY, width, height);
            setFont(selectFont(typeField, sizeFont));
            setForeground(Color.decode("#FFFBFB"));
            setHorizontalAlignment(CENTER);
            setOpaque(false);

            addMouseListener(new MouseAdapter() {
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    colors = 1;
                    setForeground(Color.decode("#FFFBFB"));
                    repaint();
                }

                public void mouseEntered(MouseEvent e) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    colors = 2;
                    setForeground(Color.decode("#FFFBFB"));
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    colors = 1;
                    setForeground(Color.decode("#FFFBFB"));
                    repaint();
                }
            });

        }

        protected void paintComponent(Graphics g) {
            //Seleciona cor, posi��o e pinta o Background
            g.setColor(selectColor(colors));
            g.fillRoundRect(0, 0, getWidth()-1, getHeight(), 10, 10);
            super.paintComponent(g);
        }
    }

    public class LOGOO extends JLabel {

        protected void paintComponent(Graphics g) {
            //Seleciona cor, posi��o e pinta o Background
            g.setColor(Color.white);
            g.fillRoundRect(0, 0, getWidth()-1, getHeight(), 300, 300);
            super.paintComponent(g);
        }
    }
}
