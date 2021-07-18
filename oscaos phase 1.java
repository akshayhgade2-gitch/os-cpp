import java.io.*;
import java.util.*;

public class oscaos
{
   
    public static void main(String[] args)throws IOException 
    {
     char ar[][]=new char[100][4];
     //char b1,b2,b3,b4;
     String str,instruct,data,reg1="";
     int gdblk=0,jbid=0,jbin=0,jbpd=0,togreg=0;
     File f1=new File("B:/VIT/sy/caos_project/New folder/temp.txt");
     FileWriter f2=new FileWriter("B:/VIT/sy/caos_project/New folder/output.txt");
     Scanner sc=new Scanner(f1);
     while(sc.hasNextLine,())
     {
         str=sc.nextLine();
         if(str.substring(0,4).equals("$AMJ"))
         {
             jbid=Integer.parseInt(str.substring(4,8));
             jbin=Integer.parseInt(str.substring(8,12));
             jbpd=Integer.parseInt(str.substring(12,16));
             System.out.println("job id:"+jbid+" inst count:"+jbin+" line printer:"+jbpd);
         }
         else if(str.substring(0,2).equals("GD"))
         {   gdblk=Integer.parseInt(str.substring(2,4));
             int ct=0;
             while(ct<(str.length()-1))
             {int j=0;
             
                while(j<=str.length())
                 {int k=0;
                    try {
                        if(Character.toString(str.charAt(ct)).equals("H"))
                        {
                            ar[j][k]=str.charAt(ct);
                                    System.out.print(j+" ");
                                    System.out.print(k+" ");
                                    System.out.println(ar[j][k]);
                                   // System.out.println("end of code");
                                    //System.out.println(ct);
                                    j++;ct++;
                        }
                        else
                        {
                           
                            while(k<=3)
                            {
                            ar[j][k]=str.charAt(ct);
                                System.out.print(j+" ");
                                System.out.print(k+" ");
                                System.out.println(ar[j][k]);
                                k++;ct++;//System.out.println("k");
                            }j++;
                           
                        }    
                    } catch (Exception e) {
                        System.out.println("");break;  
                    }
                    
                    //System.out.println("j");
                 }//System.out.println("ct");
             }             
        }
        else if(str.substring(0,5).equals("$DATA"))
        {System.out.println("inside data");
            str=sc.nextLine();
            int ct=0;
            while(ct<str.length())
            {int j=gdblk;//System.out.println("ct"+ct);
                
                try {while(j<gdblk+10)
                    {int k=0;
                    while(k<=3)
                            {//System.out.println("ct"+ct);
                            ar[j][k]=str.charAt(ct);
                                System.out.print(j+" ");
                                System.out.print(k+" ");
                                System.out.println(ar[j][k]);
                                k++;ct++;//System.out.println("k");
                            }j++;//System.out.println("j"+j);
                //System.out.println("ct"+ct);
                        }
                } catch (Exception e) {
                    System.out.println("");
                }
                    

            }
        }

        else if(str.substring(0,5).equals("$ENDJ"))
        {
            String inst="";
            /*for(int i=1;i<jbin;i++)
            {*/int i=1;
                while(i<jbin)
                {
                for(int j=0;j<4;j++)
                {
                    inst=inst+ar[i][j];
                   // System.out.println("i:"+i+"j:"+j+""+inst);
                }
                
                if(inst.substring(0,2).equals("PD"))
                {int add=Integer.parseInt(inst.substring(2,4));
                    String outp="";
                    for(int j=0;j<10;j++)
                    {
                        for(int k=0;k<4;k++)
                        {
                            outp=outp+ar[add][k];
                            //System.out.println("output: "+outp+" ");
                            //System.out.println(add+" "+k+""+ar[add][k]);
                            System.out.print(ar[add][k]);
                        }add++;
                    }try {
                        f2.write(outp+"\n");
                    } catch (Exception e) {
                        //TODO: handle exception
                    } inst="";
                }
                
                else if(inst.substring(0,2).equals("LR"))
                {
                    int add=Integer.parseInt(inst.substring(2,4));
                    for(int k=0;k<4;k++)
                    {
                        reg1=reg1+ar[add][k];
                        System.out.println("\nadd:"+add+" k:"+k+" reg:"+reg1);
                    }
                    System.out.println(reg1);
                    inst="";
                }

                else if(inst.substring(0,2).equals("SR"))
                {
                    int add=Integer.parseInt(inst.substring(2,4));
                    for(int k=0;k<4;k++)
                    {
                        ar[add][k]=reg1.charAt(k);
                        System.out.println("\nadd:"+add+" k:"+k+" val: "+ar[add][k]);
                    }
                    inst="";
                }
                else if(inst.substring(0,2).equals("CR"))
                {
                    int add=Integer.parseInt(inst.substring(2,4));
                    String strd="";
                    for(int k=0;k<4;k++)
                    {
                        strd=strd+ar[add][k];
                    }

                    if(reg1.equals(strd))
                    {
                        togreg=0;
                    }
                    else
                    {
                        togreg=1;
                    }
                    System.out.println("togreg:"+togreg);
                    inst="";
                }

                else if(inst.substring(0,2).equals("BT"))
                {
                    int add=Integer.parseInt(inst.substring(2,4));
                    if(togreg==1)
                    {inst="";
                        for(int j=0;j<4;j++)
                            {
                                 inst=inst+ar[add][j];
                                //System.out.println("i:"+i+"j:"+j+"-"+inst);
                                continue;
                            }
                        //System.out.println("i:"+add+" "+inst);continue;
                    }//inst="";i=add+4;break; 
                    else
                    {
                        inst="";
                     
                    }
                    
                }

                else if(inst.substring(0,1).equals("H"))
                {
                    System.out.println("\n\njob terminated\n");
                    if(sc.hasNextLine())
                        {
                            //sc.nextLine();sc.nextLine();
                            f2.write("\n\n");
                            break;
                        }
                        else
                        {
                            System.out.println("\n=>End of Batch");
                        }
                                   
                }
                

                i++;
            }
            //}
        }
     }
     
     f2.close();
    }
}