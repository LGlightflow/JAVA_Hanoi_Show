package Hanoi;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

 
 
public class MainWindow extends JFrame implements ActionListener {
	Tower tower = null;
	int discNum = 3;
	int speed = 0;
	SpinnerModel discNumSel;
	JSpinner discNumSelSpinner;

	JButton startOrReplay;
	JButton autoPlayBtn ;
	JButton pauseBtn;
	JButton continueBtn;

	JLabel playSpeed;
	SpinnerModel speedSel;
	JSpinner speedSelSpinner;
	JPanel center = new JPanel();
	Timer time;
	MainWindow() {
		setTitle("Hanoi Game");
		time = new Timer(100, this);
		time.setInitialDelay(10);

		tower = new Tower();
		tower.setDiscNum(discNum);
		tower.setMaxDiscWidth(120);
		tower.setMinDiscWidth(50);
		tower.setDiscHeight(16);
		tower.putDiscOnTower();
		add(tower, BorderLayout.CENTER);
		startOrReplay = new JButton("开始/重置");
		startOrReplay.addActionListener(this);
		autoPlayBtn = new JButton("自动求解");
		autoPlayBtn.addActionListener(this);
		discNumSel = new SpinnerNumberModel(3, 3, 10, 1);
		discNumSelSpinner = new JSpinner(discNumSel);
		JLabel discNumSelLabel = new JLabel("盘数");
		pauseBtn = new JButton("暂停");
		pauseBtn.addActionListener(this);
		continueBtn = new JButton("继续");
		continueBtn.addActionListener(this);
		JLabel playSpeed = new JLabel("速度");
		speedSel = new SpinnerNumberModel(10, 1, 10, 1);
		speedSelSpinner = new JSpinner(speedSel);
		JPanel south = new JPanel();
		

		south.add(startOrReplay);
		south.add(autoPlayBtn);
		south.add(discNumSelLabel);
		south.add(discNumSelSpinner);
		south.add(pauseBtn);
		south.add(continueBtn);
		south.add(playSpeed);
		south.add(speedSelSpinner);
		add(south, BorderLayout.SOUTH);



		setResizable(false);
		setVisible(true);
		setBounds(60, 60, 500, 400);
		//组件布局有效
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
	public void actionPerformed(ActionEvent e) {
		discNum = (int)discNumSelSpinner.getValue();
		speed = (int)speedSelSpinner.getValue();
		if (e.getSource() == time) {
			tower.getAutoMoveDisc().number++;
			char cStart, cEnd;
			if (tower.getAutoMoveDisc().i <= tower.getAutoMoveDisc().moveStep.length() - 2) {
				cStart = tower.getAutoMoveDisc().moveStep.charAt(tower.getAutoMoveDisc().i);
				cEnd = tower.getAutoMoveDisc().moveStep.charAt(tower.getAutoMoveDisc().i + 1);
				tower.getAutoMoveDisc().autoMoveDisc(cStart, cEnd,speed);
			}
			tower.getAutoMoveDisc().i = tower.getAutoMoveDisc().i + 2;
			//移动完毕
			if (tower.getAutoMoveDisc().i >= tower.getAutoMoveDisc().moveStep.length() - 1) {
				time.stop();
			}
		}else if (e.getSource() == startOrReplay) {
			tower.setDiscNum(discNum);
			tower.putDiscOnTower();
		} else
		if (e.getSource() == autoPlayBtn) {
			if (tower.getAutoMoveDisc().moveStep.length() == 0) {
				if (time.isRunning() == false) {
					tower.getAutoMoveDisc().i = 0;
					tower.getAutoMoveDisc().moveStep = new StringBuffer();
					tower.getAutoMoveDisc().setMoveStep(
							discNum, tower.getAutoMoveDisc().towerName[0],
							tower.getAutoMoveDisc().towerName[1],
						 tower.getAutoMoveDisc().towerName[2]);
					tower.getAutoMoveDisc().number = 0;
					time.start();
				}
			}
		}else if(e.getSource() == pauseBtn){
			
			if (time.isRunning() == true)
				time.stop();
		}else if(e.getSource() == continueBtn){
			System.out.println("");
			if (time.isRunning() == false)
				time.restart();
		}
	
		validate();
	}

	
	public static void main(String args[]) {
		new MainWindow();
	}
}