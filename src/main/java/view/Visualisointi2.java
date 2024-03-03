package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Visualisointi2 extends Canvas implements IVisualisointi{
	
	private GraphicsContext gc;
	ArrayList<String> virheet = new ArrayList<>();
	
	int asiakasLkm = 0;

	public Visualisointi2(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}
	

	public void tyhjennaNaytto() {
		gc.clearRect(0,0, this.getWidth(), this.getHeight());
		gc.setFill(Color.GREY);
		gc.fillRect(0,0,this.getWidth(), this.getHeight());
	}
	
	public void uusiAsiakas() {
		
		asiakasLkm++;

		double x = this.getWidth()/2;
		double y = this.getHeight()/2;

		gc.clearRect(x - 50, y - 10, 100, 20);
		
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		gc.setFill(Color.DARKORANGE);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(new Font(20));
		gc.fillText("Asiakas " + asiakasLkm, x, y);
		
	}


	@Override
	public void naytaVirheIlmoitus(String virhe) {
		double y = this.getHeight()/2;
		virheet.add(virhe);
		gc.setFill(Color.GRAY);
		gc.fillRect(0,0,this.getWidth(),this.getHeight());
		gc.setFill(Color.DARKORANGE);
		gc.setFont(new Font(15));
		gc.setTextAlign(TextAlignment.CENTER);
		for (String s : virheet) {
			gc.fillText(s, this.getWidth()/2, y += 20);
		}
		virheet.clear();
	}

	@Override
	public void setLoppuaika(double aika, double hcr, int i) {}
}
