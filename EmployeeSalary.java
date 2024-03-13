/**
 * Program description: store the employee names, annual salaries, and years of service and display the top-performing and the least years of service employee.
 * programmer:BQ
 * Date:13 March 2024
 */

//import the respective packages
import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

//driver class
public class EmployeeSalary
{
    //Driver method
    public static void main(String[]args) throws IOException
    {
        //Instantiate the object of DecimalFormat
        DecimalFormat dF = new DecimalFormat("0.00");

        try
        {
            //Set the input/output file
            //input file
            BufferedReader inputFile = new BufferedReader(new FileReader("employeeSalaries.txt"));

            //2 output files
            PrintWriter employData = new PrintWriter(new FileWriter("employeeData.txt"));

            //Declare the variables
            String inputData = null;
            String employName;
            double yearsOfService;
            int baseSalary,annSalary;

            //Write the title of the employee details to the employeeData.txt
            employData.println("List of the top-performing employee");
            employData.println("\nEmployee's Name\t\tBase Salary(RM)\t\tService years");
            employData.println("----------------------------------------------------------------------------------------------------");

            //Write the title of the employee details to the employeeData.txt
            employData.println("List employee with the least years of service");
            employData.println("\nEmployee's Name\t\tService years");
            employData.println("----------------------------------------------------------------------------------------------------");

            int countEmployData = 0;

            while((inputData = inputFile.readLine()) != null)
            {
                //INstantiate the object reference of the StringTokenizer
                //to pass the string line (input data) & to set the delimeter
                StringTokenizer sT = new StringTokenizer(inputData,"|");
                int tokensCount = sT.countTokens();
                
                //Check if there are enough token available
                if (tokensCount < 4)
                {
                    throw new IllegalArgumentException("Invalid input format: Insufficient data");
                }
                
                //to pass the string line & delimeter
                //Break into tokens and assign to the appropriate variables
                employName = sT.nextToken();

                //or
                yearsOfService =Double.parseDouble(sT.nextToken());
                annSalary =Integer.parseInt(sT.nextToken());
                baseSalary =Integer.parseInt(sT.nextToken());
                
                // Calculate annual salary (base salary + 5%)
                annSalary = baseSalary + (int) (baseSalary * 0.05);

                //To test for the negative number
                if(yearsOfService < 0)
                    {
                        throw new IllegalArgumentException("Invalid input format: Negative years of service");
                    }
                
                //Initialize the category of null
                String catergory = null;
                if (yearsOfService < 2)
                {
                    //Display employee with the least years of service
                    employData.println(employName + "\t\t" + yearsOfService);
                }
                
                else if (annSalary >= 50 && annSalary <= 200)
                {
                    //Display the top-performing employee
                    employData.println(employName + "\t\t" + annSalary + "\t\t" + yearsOfService);
                }
            }//end of while loop

            //close all the input/output files
            //Close the input/output file
            inputFile.close();
            employData.close();
            employData.close();
        }//end of try block

        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }//end of catch
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input! The salary must be a positive number";
            JOptionPane.showMessageDialog(null, output);
        }//end of catch
    }//end of main
} //end of class