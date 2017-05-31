/* @author Thiago Bressanin */
package javaapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaApp {

    public static void main(String[] args) throws FileNotFoundException, IOException 
    {                      
        List<Parametro> lista = new ArrayList<>();
        FileReader fr = new FileReader("src/javaapp/log.txt");
        BufferedReader br = new BufferedReader(fr);
        String temp;
        while ((temp = br.readLine()) != null) {
            String code = "";
            String url = "";
            Pattern p = Pattern.compile("request_to=" + '"' + "(.*?)" + '"');
            Matcher m = p.matcher(temp);
            if (m.find()) {
                url = m.group(1);
            }
            Pattern p2 = Pattern.compile("response_status=" + '"' + "(.*?)" + '"');
            Matcher m2 = p2.matcher(temp);

            if (m2.find()) {
                code = m2.group(1);
            }
            if (url != "") {
                Parametro wtemp = new Parametro(url, code);
                lista.add(wtemp);
            }
        }

        Set<String> s = new TreeSet<>();
        Set<String> u = new TreeSet<>();

        for (Parametro wh : lista) {
            s.add(wh.getCode());
            u.add(wh.getUrl());
        }
        Iterator it = s.iterator();
        Iterator it2 = u.iterator();

        Code setCode[] = new Code[s.size()];
        Url setUrl[] = new Url[u.size()];
        
        int incrementador = 0;
        int incrementadoru = 0;        

        //Iteração QUANTIDADE DE RESPOSTAS
        while (it.hasNext()) {
            String stemp;
            int scont = 0;
            stemp = (String) it.next();

            for (int i = 0; i < lista.size(); i++) {
                if (stemp.equals(lista.get(i).getCode())) {
                    scont++;
                }
            }
            setCode[incrementador] = new Code((stemp), scont);
            incrementador++;
        }

        while (it2.hasNext()) {
            String stemp;
            int scont = 0;

            stemp = (String) it2.next();

            for (int i = 0; i < lista.size(); i++)
            {
                if (stemp.equals(lista.get(i).getUrl()))
                    scont++;                
            }
            setUrl[incrementadoru] = new Url((stemp), scont);
            incrementadoru++;
        }

        setUrl = (Url[]) ordenaVetor(setUrl);
        setCode = (Code[]) ordenaVetor(setCode);

        /*Exibe os valores limitados a 3 itens requisitados
         setUrl -> request_to"<url>" e setCode -> response_status="<code>"*/
        exibeValor(3, setUrl);
        exibeValor(2, setCode);
        
    }

    public static void exibeValor(int quantidade, Object[] e) 
    {
        int count = 0;

        if (e instanceof Url[]) 
        {
            System.out.println("Chamadas URL");
            Url[] obj = (Url[]) e;
            for (Url o : obj) 
            {                
                System.out.println(o.getUrl() + " - " + o.getQuantidade());
                count++;
                if (count == quantidade) break;                
            }
        } else if (e instanceof Code[]) 
        {
            System.out.println("\nCodigo de resposta");
            Code[] obj = (Code[]) e;
            for (Code o : obj) 
            {
                System.out.println(o.getCode() + " - " + o.getQuantidade());
                count++;
                if (count == quantidade)break;                
            }
        }
    }

    public static Object ordenaVetor(Object[] e) 
    {          
        //Ordena em ordem decrescente vetores do tipo 'Code' - O(n^2) - Linear
        if (e instanceof Code[])
            {            
                Code[] code = (Code[]) e;
                for (int i = 0; i < e.length; i++)
                {
                    for (int k = 0; k < e.length; k++) 
                    {
                        if (code[i].getQuantidade() > code[k].getQuantidade()) 
                        {
                            Code temp = (Code) e[i];
                            e[i] = e[k];
                            e[k] = temp;                          
                        }
                    }
                }
            }
        
            //Ordena em ordem decrescente vetores do tipo 'Url' - O(n^2) - Linear
            if (e instanceof Url[]) {                
                Url[] url = (Url[]) e;              
                
                for (int i = 0; i < e.length; i++) {
                    for (int k = 0; k < e.length; k++) 
                    {
                        if (url[i].getQuantidade() > url[k].getQuantidade()) 
                        {
                            Url temp = (Url) e[i];
                            e[i] = e[k];
                            e[k] = temp;  
                        }
                    }
                }
            }
            return e;
            
        }
    
    
} 
