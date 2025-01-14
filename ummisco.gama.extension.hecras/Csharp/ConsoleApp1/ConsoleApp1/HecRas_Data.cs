﻿using RAS506;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks; 

namespace ConsoleApp1
{
    /*
    public interface ICalc
    {
        string MySuperSmartFunctionIDontHaveInJava(string filename, string gate_name);
    }
    */


    public class HecRas_Data //: ICalc
    {
        HECRASController hrc;

        public string Init_hecras()
        {
            hrc = new HECRASController();
            string result = "";
            return result;
        }

        public string Project_Open(string path)
        {
            hrc.Project_Open(path);
            return "";
        }

        public string Compute_ShowComputationWindow()
        {

            hrc.Compute_ShowComputationWindow();
            return "";
        }

        public bool isComplete()
        {
            return hrc.Compute_Complete();
        }

        public string Compute_CurrentPlan()
        {
            int nmsg = 0;
            bool block = true;
            Array arr = null;// CreateInstance(typeof(string), 0);
            //string[] arr = new string[1] {null};
            string result = "";
            try
            {
                hrc.Compute_HideComputationWindow();
                result = hrc.CurrentGeomHDFFile();
                hrc.Compute_CurrentPlan( 0,  null,true);
                /*
                hrc.Output_Initialize();
                hrc.Output_ComputationLevel_Export("E:\\aa.txt", result, true,true,true,true);
                //hrc.ShowRasMapper();
                hrc.ExportGIS();
                hrc.Output_GetProfiles(0,arr);
                hrc.PlotPFGeneral("", "");
                hrc.ShowRas();
                hrc.Geometry().Save();
                */
                //Console.WriteLine(hrc.Plan_GetFilename());
            }
            catch (Exception ex)
            {
                result=ex.ToString();
            }
            //hrc.Project_Close();
            //hrc.QuitRas();
            hrc = null;
            return result;
        }

        public string Project_Save()
        {
            hrc.Project_Save();
            return "";
        }
        public string Project_Close()
        {
            hrc.Project_Close();
            return "";
        }
        public string QuitRas()
        {
            hrc.QuitRas();
            return "";
        }

        /*
         * 
        public void asd()
        {
            HECRASController hrc = new HECRASController();
            int nmsg = 0;
            bool block = true;
            Array sa = null;
            hrc.Project_Open(@"C:\HEC Data\HEC-RAS\Example Data\2D Unsteady Flow Hydraulics\Muncie\Muncie.prj");
            hrc.Compute_ShowComputationWindow();

            try
            {
                hrc.Compute_CurrentPlan(ref nmsg, ref sa, ref block);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
            hrc.Project_Save();
            hrc.Project_Close();
            hrc.QuitRas();
        }
        */
    }
}
