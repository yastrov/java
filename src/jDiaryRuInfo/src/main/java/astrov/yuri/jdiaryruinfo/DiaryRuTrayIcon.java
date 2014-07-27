/*
 *The MIT License (MIT)
 *
 *Copyright (c) 2014 Yuri Astrov
 *
 *Permission is hereby granted, free of charge, to any person obtaining a copy
 *of this software and associated documentation files (the "Software"), to deal
 *in the Software without restriction, including without limitation the rights
 *to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *The above copyright notice and this permission notice shall be included in all
 *copies or substantial portions of the Software.
 *
 *THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *SOFTWARE.
 */

package astrov.yuri.jdiaryruinfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Yuri Astrov on 26.07.14.
 */
public class DiaryRuTrayIcon implements ActionListener, AuthDataListener {
    private static final String DEFAULT_ICON_NAME = "deficon.png";
    private static final String ATTENTION_ICON_NAME = "aticon.png";
    
    public static final String APPLICATION_NAME = "jDiaryRuInfo";
    private static final String TIMER_EXIT_COMMAND = "TIMER_EXIT_COMMAND";
    private static final String POPUPMENU_AUTHORIZE_COMMAND = "Authorize";
    private static final String POPUPMENU_EXIT_COMMAND = "Exit";
    private static final String POPUPMENU_MANUALLYCHECK_COMMAND = "Manually check";
    private static final String ERROR_STRING = "Error with connecting to server!";
    private javax.swing.Timer timer = null;
    private static final int timerDelay = 1000; //seconds multiplier
    private TrayIcon trayIcon = null;
    private Image defaultIcon = null;
    private Image attentionIcon = null;
    private DiaryRuClient client = null;

    public DiaryRuTrayIcon(){
        client = new DiaryRuClient();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DiaryRuTrayIcon tIc = new DiaryRuTrayIcon();
                try {
                    tIc.loadDefaultIcon();
                    tIc.loadAttentionIcon();
                } catch (NullPointerException | IOException ex) {
                    ex.printStackTrace();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Plaese, create icon files:").append("\n")
                            .append(DEFAULT_ICON_NAME).append("\n")
                            .append(ATTENTION_ICON_NAME);
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
                tIc.createTrayIcon();
                tIc.authorize();
            }
        });
    }

    /**
     * Load default Tray Icon from Image file. 
     */
    private void loadDefaultIcon() throws NullPointerException, IOException {
        URL imageURL = getClass().getResource(DEFAULT_ICON_NAME);
        defaultIcon = Toolkit.getDefaultToolkit().getImage(imageURL);
    }
    
    /**
     * Load attention Tray Icon from Image file. 
     */
    private void loadAttentionIcon() throws NullPointerException {
        URL imageURL = getClass().getResource(ATTENTION_ICON_NAME);
        attentionIcon = Toolkit.getDefaultToolkit().getImage(imageURL);
    }

    /**
     * Set Tray Icon to default. 
     */
    public void setDefaultIcon(){
        trayIcon.setImage(defaultIcon);
    }

    /**
     * Set Tray Icon to attention.
     */
    public void setAttentionIcon(){
        trayIcon.setImage(attentionIcon);
    }

    /**
     * Display custom message for Tray Icon.
     * @param message
     */
    public void displayTrayMessage(String message) {
        trayIcon.displayMessage(APPLICATION_NAME, message,
                TrayIcon.MessageType.INFO);
    }

    /**
     * Wrapper for create item for popup menu.
     * @param menu PopupMenu object
     * @param menuName String menu name
     */
    private void createPopupMenuItem(PopupMenu menu, String menuName) {
        MenuItem item = new MenuItem(menuName);
        item.addActionListener(this);
        menu.add(item);
    }

    /**
     * Create and configure Tray Icon.
     */
    private void createTrayIcon() {
        if(! SystemTray.isSupported() ) {
            return;
        }

        PopupMenu trayMenu = new PopupMenu();
        createPopupMenuItem(trayMenu, POPUPMENU_MANUALLYCHECK_COMMAND);
        createPopupMenuItem(trayMenu, POPUPMENU_AUTHORIZE_COMMAND);
        trayMenu.addSeparator();
        createPopupMenuItem(trayMenu, POPUPMENU_EXIT_COMMAND);

        if(defaultIcon == null) {
            StringBuilder sb = new StringBuilder();
            System.out.println("Hello from Tray");
            sb.append("Plaese, create icon files:").append("\n")
                        .append(DEFAULT_ICON_NAME).append("\n")
                        .append(ATTENTION_ICON_NAME);
            JOptionPane.showMessageDialog(null, sb.toString());
        }
        trayIcon = new TrayIcon(defaultIcon, APPLICATION_NAME, trayMenu);
        trayIcon.setImageAutoSize(true);
        
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create timer and set delay
     * @param delay int value in secunds.
     */
    private void startTimer(int delay) {
        timer = new javax.swing.Timer(delay*timerDelay, this);
        timer.setActionCommand(TIMER_EXIT_COMMAND);
        timer.start();
    }
    /**
     * Set Delay for timer
     * @param delay int value in secunds.
     */
    public void setTimerDelay(int delay) {
        timer.setDelay(delay*timerDelay);
    }
    
    /**
     * Create and show GUI form for User Authorization
     */
    public void authorize() {
        if (timer != null) timer.stop();
        //NewJFrame aFrame = new NewJFrame();
        NewJFrame.main(this);
        
    }

    /**
     * Process events for timer and popup menu.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case POPUPMENU_EXIT_COMMAND:
                if (timer != null) timer.stop();
                System.exit(0);
                break;
            case POPUPMENU_AUTHORIZE_COMMAND:
                authorize();
                break;
            case TIMER_EXIT_COMMAND:
                checkInfo();
                break;
            case POPUPMENU_MANUALLYCHECK_COMMAND:
                checkInfo();
                break;
        }
    }
    
    /**
     * Handler for receive data from Authorization Form.
     * @param login
     * @param password
     * @param delay 
     */
    @Override
    public void processAuthData(String login, String password, String delay) {
        startTimer(Integer.valueOf(delay));
        try {
            client.auth(login, password);
            checkInfo();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Request to remote web server and show messages.
     */
    private void checkInfo() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DiaryRuInfo info = client.getDiaryRuInfo();
                    if(info.isError())
                        JOptionPane.showMessageDialog(null, info.toString());
                    trayIcon.setToolTip(info.toString());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            ERROR_STRING);
                    trayIcon.setToolTip(ERROR_STRING);
                    ex.printStackTrace();
                }
            }
        });
    }
}
