package Test;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Genotype;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;

import Fitness.MinimizeFitnessFunction;
import Functions.GenetikAlgoritmaParametre;
import Functions.SigmoidalTransfer;
import Models.Cromosoma;
import Models.MultiLayerPerceptron;
import Utils.GenetikUtils;
import Utils.ImageProcessingBW;
import Utils.UygulamaParametre;

public class PatterRecognitionGATest {
	private static String directory = System.getProperty("user.dir");
	private static final int GENERACION = 10;
	private static final int ITERACION_MLP = 50;
	private static final int SIZEX = 32;
	private static final int SIZEY = 49;
	private static final int NIMAGESXPATT = 22;
	private static final int NPATT = 10;
	
	
	
	/**
	 * @param args
	 * @throws InvalidConfigurationException 
	 */
	public static void main(String[] args) throws InvalidConfigurationException 
	{	
		new Thread() {
			@Override
			public void run() {
				ejecutarAlgoritmo("Letras");
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				ejecutarAlgoritmo("Numeros");
				
			}
		}.start();

		
		/*int[] layers = new int[]{ sizex*sizey, sizey, npatt };
		
		Configuration conf = new DefaultConfiguration();
		conf.setPreservFittestIndividual(true);
		conf.setKeepPopulationSizeConstant(false);
		FitnessFunction myFunc = new MinimizeFitnessFunction(0);
		conf.setFitnessFunction(myFunc);
		
		Genotype poblacion = Genotype.randomInitialGenotype(conf);
		
		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.6, new SigmoidalTransfer());
		*/
		//TestPrecision("Numeros", generacion, sizex, sizey, nimagesxpatt, npatt);
		
	}
	
	private static void ejecutarAlgoritmo(String CharacterType) {
		try {
			long startTime = System.currentTimeMillis();
			List<Cromosoma> pCromosoma = new ArrayList<Cromosoma>();
	        List<Cromosoma> pCromosomaNuevaGeneracion = new ArrayList<Cromosoma>();
	        List<Cromosoma> pCromosomaNuevaGeneracionCaparazon = new ArrayList<Cromosoma>();
	
	        List<String> listaCromosomasAleatoros;
		
			listaCromosomasAleatoros = GenetikUtils.creacionCromosoma(UygulamaParametre.CROMOSOMAS_ALEATORIOS, UygulamaParametre.GENOM_TOTAL_LONGITUD);
		


	        System.out.println("Preparación de cromosoma aleatorio y asignación de valores de elegibilidad********");
	        prepararCromosomasAlAzar(CharacterType, listaCromosomasAleatoros,pCromosoma);
	        for (int z = 0; z < GENERACION; z++) {
	        	System.out.println();
                System.out.println(z+". Asignación de valor acumulado********");
                numeroValorAcumulativo(pCromosoma);

                System.out.println();
                System.out.println(z+". La ruleta rusa comienza la selección de cromosomas*******");
                ruletaRusaConSeleccion(pCromosomaNuevaGeneracion,pCromosoma);
                System.out.println(z+". La ruleta rusa termina la selección de cromosomas*******");

                System.out.println();
                System.out.println(z+". Comienza el cruce*******");
                cromosomaCruce(CharacterType, pCromosomaNuevaGeneracion,pCromosomaNuevaGeneracionCaparazon);
                System.out.println(z+". Termina el cruce*******");

                System.out.println();
                System.out.println(z+". Comienza la mutación*******");
                mutacion(pCromosomaNuevaGeneracionCaparazon);
                System.out.println(z+". Termina la mutación*******");

                System.out.println();
                System.out.println(z+". Comienza la selección natural*******");
                seleccionNatural(pCromosomaNuevaGeneracionCaparazon,pCromosoma);
                System.out.println(z+". Termina la selección natural*******");


                pCromosoma = pCromosomaNuevaGeneracionCaparazon;
                pCromosomaNuevaGeneracion = new ArrayList<Cromosoma>();
                pCromosomaNuevaGeneracionCaparazon = new ArrayList<Cromosoma>();
                System.out.println();
                System.out.println("++Nueva generación++");
                System.out.println();
	        }
	        System.out.println("Fin...");
            for (Cromosoma cromosoma : pCromosoma) {

                System.out.println("Cromosoma " + cromosoma.getCromosoma() + " Valor de conformidad " + cromosoma.getUygunlukDegeri());
            }
            Cromosoma elitCromosoma = secuenciaCromosomaElite(pCromosoma);
            System.out.println();
            System.out.println("El mejor resultado: cromosoma " + elitCromosoma.getCromosoma() + " Valor de conformidad " + elitCromosoma.getUygunlukDegeri());

           System.out.print(elitCromosoma);
           TestPrecision(CharacterType, ITERACION_MLP, SIZEX, SIZEY, NIMAGESXPATT, CharacterType.equals("Numeros")? NPATT : NPATT+16,
        		   binaryToInt(elitCromosoma.getCromosoma().substring(UygulamaParametre.GENOM1_LONGITUD + UygulamaParametre.GENOM2_LONGITUD, UygulamaParametre.GENOM_TOTAL_LONGITUD - 1)),
        		   binaryToInt(elitCromosoma.getCromosoma().substring(0, UygulamaParametre.GENOM1_LONGITUD - 1)) / (double) 100, true);
           long endTime = System.currentTimeMillis() - startTime; // tiempo en que se ejecuta su for 
           System.out.println(CharacterType+": Se ejecutó "+endTime/1000/60+" minutos.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void seleccionNatural(List<Cromosoma> pCromosomaNuevaGeneracionCaparazon, List<Cromosoma> pCromosoma) {
		 System.out.println("Débil eliminado**** ELÄ°TÄ°ZM");

		 Cromosoma elitCromosoma = secuenciaCromosomaElite(pCromosoma);
		 pCromosomaNuevaGeneracionCaparazon.add(elitCromosoma);
		 System.out.println("Cromosoma de élite " + elitCromosoma.getCromosoma());
	}
	private static Cromosoma secuenciaCromosomaElite(List<Cromosoma> pCromosoma) {

		Cromosoma elit = new Cromosoma();

		for (Cromosoma cromosoma : pCromosoma) {
			if (elit.getUygunlukDegeri() < cromosoma.getUygunlukDegeri()) {
				elit = cromosoma;
			}
			
		}
		return elit;
	}
	
	private static void mutacion(List<Cromosoma> pCromosomaNuevaGeneracionCaparazon) {

        System.out.println("La mutación se aplica al cromosoma seleccionado*******");
        int ordenCromosoma = secuenciaDeMutacionAAplicar(pCromosomaNuevaGeneracionCaparazon);

        System.out.println("Cromosoma de mutación " + pCromosomaNuevaGeneracionCaparazon.get(ordenCromosoma).getCromosoma());

        Cromosoma comosomaMutaAplicado = aplicarMutacion(pCromosomaNuevaGeneracionCaparazon, ordenCromosoma);
        System.out.println("Cromosoma de mutación   " + comosomaMutaAplicado.getCromosoma());
    }
	
	private static int secuenciaDeMutacionAAplicar(List<Cromosoma> pCromosomaNuevaGeneracionCaparazon) {
        Random generator = new Random();
        int ordenCromosoma = generator.nextInt(pCromosomaNuevaGeneracionCaparazon.size());

        return ordenCromosoma;
    }
	
	private static Cromosoma aplicarMutacion(List<Cromosoma> pCromosomaNuevaGeneracionCaparazon, int ordenCromosoma) {
        Random generator = new Random();
        int genCromosomaDegradado = generator.nextInt(UygulamaParametre.GENOM_TOTAL_LONGITUD);
        System.out.println("Orden del cromosoma para mutar " + genCromosomaDegradado);
        String cambioCromosoma = pCromosomaNuevaGeneracionCaparazon.get(ordenCromosoma).getCromosoma();
        String cambioGen = cambioCromosoma.substring(genCromosomaDegradado, genCromosomaDegradado + 1);

        if ("1".equals(cambioGen)) {
        	cambioGen = "0";
        } else {
        	cambioGen = "1";
        }
        cambioCromosoma = cambioCromosoma.substring(0, genCromosomaDegradado) + cambioGen + cambioCromosoma.substring(genCromosomaDegradado + 1, cambioCromosoma.length());

        pCromosomaNuevaGeneracionCaparazon.get(ordenCromosoma).setCromosoma(cambioCromosoma);
        return pCromosomaNuevaGeneracionCaparazon.get(ordenCromosoma);
    }
	
	private static void cromosomaCruce(String CharacterType, List<Cromosoma> pCromosomaNuevaGeneracion, List<Cromosoma> pCromosomaNuevaGeneracionCaparazon) {

        for (int i = 0; i < UygulamaParametre.NUMERO_CROMOSOMA_CRUZA; i = i + 2) {
            Cromosoma cruceCromosoma = cruce(pCromosomaNuevaGeneracion.get(i), pCromosomaNuevaGeneracion.get(i + 1));
            cruceCromosoma.setUygunlukDegeri(calculoValorAptitud(CharacterType, cruceCromosoma.getCromosoma()));
            pCromosomaNuevaGeneracionCaparazon.add(cruceCromosoma);
            System.out.println("La primera parte del cromosoma " + pCromosomaNuevaGeneracion.get(i).getCromosoma() + " + " + pCromosomaNuevaGeneracion.get(i + 1).getCromosoma() + "-->> " + cruceCromosoma.getCromosoma() + " Valor de conformidad " + cruceCromosoma.getUygunlukDegeri());

            System.out.print(cruceCromosoma);

            cruceCromosoma = cruce(pCromosomaNuevaGeneracion.get(i + 1), pCromosomaNuevaGeneracion.get(i));
            cruceCromosoma.setUygunlukDegeri(calculoValorAptitud(CharacterType, cruceCromosoma.getCromosoma()));
            pCromosomaNuevaGeneracionCaparazon.add(cruceCromosoma);
            System.out.println("La segunda parte del cromosoma " + pCromosomaNuevaGeneracion.get(i + 1).getCromosoma() + " + " + pCromosomaNuevaGeneracion.get(i).getCromosoma() + "-->> " + cruceCromosoma.getCromosoma() + " Valor de conformidad " + cruceCromosoma.getUygunlukDegeri());

            System.out.print(cruceCromosoma);
        }
    }
	
	private static Cromosoma cruce(Cromosoma cromosoma, Cromosoma cromosoma1) {
		Cromosoma cruceCromosoma = new Cromosoma();
		cruceCromosoma.setCromosoma(cromosoma.getCromosoma().substring(0, UygulamaParametre.SECUENCIA_CROMOSOMA_CRUZA) + cromosoma1.getCromosoma().substring(UygulamaParametre.SECUENCIA_CROMOSOMA_CRUZA, UygulamaParametre.GENOM_TOTAL_LONGITUD));
        return cruceCromosoma;
    }
	
	public static void ruletaRusaConSeleccion(List<Cromosoma> pCromosomaNuevaGeneracion, List<Cromosoma> pCromosoma) {
        for (int i = 0; i < UygulamaParametre.NUMERO_CROMOSOMA_CRUZA; i++) {
            Cromosoma cromCruza = seleccionRuletaRusa(pCromosoma);
            System.out.println("Seleccionado para topografía" + i + ". Cromosoma " + cromCruza.getCromosoma());
            pCromosomaNuevaGeneracion.add(cromCruza);
        }
    }
	
	private static Cromosoma seleccionRuletaRusa(List<Cromosoma> cromosomaList) {

		while(true) {
	        Random generator = new Random();
	        int clasificacionRuleta = generator.nextInt((int) (cromosomaList.get(cromosomaList.size() - 1).getKumilatifDegeri()));
	
	        for (Cromosoma cromosoma : cromosomaList) {
	            if (clasificacionRuleta <= cromosoma.getKumilatifDegeri() && clasificacionRuleta > cromosoma.getKumilatifDegeri() - cromosoma.getUygunlukDegeri()) {
	                return cromosoma;
	            }
	        }
        }
    }
	
	public static List<Cromosoma> numeroValorAcumulativo(List<Cromosoma> cromosomaList) {

        double total = 0;
        for (Cromosoma cromosoma : cromosomaList) {
            total = total + cromosoma.getUygunlukDegeri();
            cromosoma.setKumilatifDegeri(total);

        }

        return cromosomaList;
    }
	
	private static void prepararCromosomasAlAzar(String CharacterType, List<String> listaCromosomasAleatorios, List<Cromosoma> pCromosomas) {
		Cromosoma Cromosoma = new Cromosoma();

        String gen1 = listaCromosomasAleatorios.get(0);
        Cromosoma.setCromosoma(gen1);
        Cromosoma.setUygunlukDegeri(calculoValorAptitud(CharacterType, gen1));
        pCromosomas.add(Cromosoma);

        String gen2 = listaCromosomasAleatorios.get(1);
        Cromosoma = new Cromosoma();
        Cromosoma.setCromosoma(gen2);
        Cromosoma.setUygunlukDegeri(calculoValorAptitud(CharacterType,gen2));
        pCromosomas.add(Cromosoma);

        String gen3 = listaCromosomasAleatorios.get(2);
        Cromosoma = new Cromosoma();
        Cromosoma.setCromosoma(gen3);
        Cromosoma.setUygunlukDegeri(calculoValorAptitud(CharacterType,gen3));
        pCromosomas.add(Cromosoma);

        String gen4 = listaCromosomasAleatorios.get(3);
        Cromosoma = new Cromosoma();
        Cromosoma.setCromosoma(gen4);
        Cromosoma.setUygunlukDegeri(calculoValorAptitud(CharacterType,gen4));
        pCromosomas.add(Cromosoma);

        String gen5 = listaCromosomasAleatorios.get(4);
        Cromosoma = new Cromosoma();
        Cromosoma.setCromosoma(gen5);
        Cromosoma.setUygunlukDegeri(calculoValorAptitud(CharacterType,gen5));
        pCromosomas.add(Cromosoma);

        System.out.println("random gen1 " + gen1.toString());
        System.out.println("random gen2 " + gen2.toString());
        System.out.println("random gen3 " + gen3.toString());
        System.out.println("random gen4 " + gen4.toString());
        System.out.println("random gen5 " + gen5.toString());
	}
	
	public static double calculoValorAptitud(String CharacterType, String genom) {
        Double learningRate = (binaryToInt(genom.substring(0, UygulamaParametre.GENOM1_LONGITUD))) / (double) 100;
        Double momentumRate = (binaryToInt(genom.substring(UygulamaParametre.GENOM1_LONGITUD, UygulamaParametre.GENOM1_LONGITUD + UygulamaParametre.GENOM2_LONGITUD))) / (double) 100;
        
        String hiddenLayer = String.valueOf(binaryToInt(genom.substring(UygulamaParametre.GENOM1_LONGITUD + UygulamaParametre.GENOM2_LONGITUD, UygulamaParametre.GENOM1_LONGITUD + UygulamaParametre.GENOM2_LONGITUD + UygulamaParametre.GENOM3_LONGITUD)));


        try {

            return GenetikAlgoritmaParametre.UygunlukFonsiyonu(TestPrecision(CharacterType, ITERACION_MLP, SIZEX, SIZEY, NIMAGESXPATT, CharacterType.equals("Numeros")? NPATT : NPATT+16,Integer.parseInt(hiddenLayer),learningRate));

        } catch (Exception ex)

        {
            ex.printStackTrace();
        }


        return 0;
    }
	
	private static int binaryToInt(String substring) {
        return Integer.parseInt(substring, 2);
    }
	
	
	public static double TestPrecision(String nombre, int maxit, int sizex, int sizey, int nimagesxpatt, int npatt, int cantidadNeuronaOculta, double learningRate) {
		return TestPrecision(nombre, maxit, sizex, sizey, nimagesxpatt, npatt, cantidadNeuronaOculta, learningRate, false);
	}
	public static double TestPrecision(String nombre, int maxit, int sizex, int sizey, int nimagesxpatt, int npatt, int cantidadNeuronaOculta, double learningRate, boolean saveModel)
	{
		
//		int sizex = 32;
//		int sizey = 49;
		double error = 0.0;
//		int nimagesxpatt = 22;
//		int npatt = 26;
		int[] layers = new int[]{ sizex*sizey, cantidadNeuronaOculta, npatt };
		
		
		MultiLayerPerceptron net = new MultiLayerPerceptron(layers, learningRate, new SigmoidalTransfer());
		
		File[] characterFolder = new File(directory+"\\img\\patterns"+nombre).listFiles();
		
		double[] errores = new double[npatt];
		
		/* Aprendizaje */
		for(int i = 0; i < maxit; i++)
		{
			for(int k = 1; k < nimagesxpatt; k++)
			{
				for(int j = 1; j < npatt+1; j++)
				{		
					String pattern = directory+"\\img\\patterns"+nombre+"\\"+characterFolder[j-1].getName()+"\\"+k+".png";
					double[] inputs = ImageProcessingBW.loadImage(pattern, sizex, sizey);
					
					if(inputs == null)
					{
						System.out.println("No se encuentra el fichero "+pattern);
						continue;
						
					}
					double[] output = new double[npatt];

					for(int l = 0; l < npatt; l++)
						output[l] = 0.0;
					
					output[j-1] = 1.0;
					
					
					// Entrenamiento
					error = net.backPropagate(inputs, output);
					errores[j-1] = error;
					//System.out.println("El error del caracter "+characterFolder[j-1].getName()+" es: "+String.format ("%.2f", (float)error * 100)+"% ("+i+" "+j+" "+k+")");
				}
			}
		}
		
		System.out.println("¡Aprendizaje Completada!");
		error=1.0;
		for(double x: errores) {
			error*=x;
		}
		error/=errores.length;
		System.out.println("Errores: "+ Arrays.toString(errores));
		System.out.println("Error Promedio: "+ error*100+"%");
		
		if(saveModel) {
			net.save(directory+"\\resources\\red"+nombre+".txt");
		}
		
		return (1-error);
		
	}
}
