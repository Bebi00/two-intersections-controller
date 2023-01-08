package twointersectionsproject;

import com.Components.*;
import com.DataObjects.DataCar;
import com.DataObjects.DataCarQueue;
import com.DataObjects.DataString;
import com.DataObjects.DataTransfer;
import com.DataOnly.TransferOperation;
import com.Enumerations.LogicConnector;
import com.Enumerations.TransitionCondition;
import com.Enumerations.TransitionOperation;

public class Intersection2 {
	public static void main(String[] args) {
		
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Intersection 2";

		pn.NetworkPort = 1082;
		
		DataString green = new DataString();	 	
		green.Printable = false;
		green.SetName("green");
		green.SetValue("green");
		pn.ConstantPlaceList.add(green);

		// -------------------------------------------------------------------
		// -------------------------------Lane1--------------------------------
		// --------------------------------------------------------------------

		DataCar p1 = new DataCar();
		p1.SetName("P_a5");
		pn.PlaceList.add(p1);

		DataCarQueue p2 = new DataCarQueue();
		p2.Value.Size = 3;
		p2.SetName("P_x5");
		pn.PlaceList.add(p2);

		DataString p3 = new DataString();
		p3.SetName("P_TL3");
		pn.PlaceList.add(p3);

		DataCar p4 = new DataCar();
		p4.SetName("P_b5");
		pn.PlaceList.add(p4);

		// -------------------------------------------------------------------------------------
		// --------------------------------Lane2-----------------------------------------------
		// -------------------------------------------------------------------------------------

		DataCar p5 = new DataCar(); //p5.Printable = false;
		p5.SetName("P_a6");
		pn.PlaceList.add(p5);

		DataCarQueue p6 = new DataCarQueue(); //p6.Printable = false;
		p6.Value.Size = 3;
		p6.SetName("P_x6");
		pn.PlaceList.add(p6);

		DataString p7 = new DataString(); //p7.Printable = false;
		p7.SetName("P_TL4");
		pn.PlaceList.add(p7);

		DataCar p8 = new DataCar(); //p8.Printable = false;
		p8.SetName("P_b6");
		pn.PlaceList.add(p8);


		// ----------------------------------------------------------------------------
		// ----------------------------Exit lane 1-------------------------------------
		// ----------------------------------------------------------------------------

		DataCarQueue p17 = new DataCarQueue(); //p17.Printable = false;
		p17.Value.Size = 3;
		p17.SetName("P_o5");
		pn.PlaceList.add(p17);

		DataTransfer p18 = new DataTransfer(); //p18.Printable = false;
		p18.SetName("P_o5Exit");
		p18.Value = new TransferOperation("localhost","1081", "P_a1" );
		pn.PlaceList.add(p18);

		// ----------------------------------------------------------------------------
		// ----------------------------Exit lane 2-------------------------------------
		// ----------------------------------------------------------------------------

		DataCarQueue p19 = new DataCarQueue(); //p19.Printable = false;
		p19.Value.Size = 3;
		p19.SetName("P_o6");
		pn.PlaceList.add(p19);

		DataCar p20 = new DataCar(); //p20.Printable = false;
		p20.SetName("P_o6Exit");
		pn.PlaceList.add(p20);

		// ----------------------------------------------------------------------------
		// ----------------------------Exit lane 3-------------------------------------
		// ----------------------------------------------------------------------------

		DataCarQueue p21 = new DataCarQueue(); //p21.Printable = false;
		p21.Value.Size = 3;
		p21.SetName("P_o7");
		pn.PlaceList.add(p21);

		DataCar p22 = new DataCar(); //p22.Printable = false;
		p22.SetName("P_o7Exit");
		pn.PlaceList.add(p22);


		// -------------------------------------------------------------------------------------------
		// --------------------------------Intersection-----------------------------------------------
		// -------------------------------------------------------------------------------------------

		DataCarQueue p25 = new DataCarQueue();
		p25.Value.Size = 3;
		p25.SetName("P_I");
		pn.PlaceList.add(p25);

		// T1 ------------------------ Input LANE 1 ------------------------------------------------
		PetriTransition t1 = new PetriTransition(pn);
		t1.TransitionName = "T_u5";
		t1.InputPlaceName.add("P_a5");
		t1.InputPlaceName.add("P_x5");

		Condition T1Ct1 = new Condition(t1, "P_a5", TransitionCondition.NotNull);
		Condition T1Ct2 = new Condition(t1, "P_x5", TransitionCondition.CanAddCars);
		T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition = T1Ct1;
		grdT1.Activations.add(new Activation(t1, "P_a5", TransitionOperation.AddElement, "P_x5"));
		t1.GuardMappingList.add(grdT1);

		t1.Delay = 0;
		pn.Transitions.add(t1);

		// T2 ------------------------ Traffic Light LANE 1 ------------------------------------------------
		PetriTransition t2 = new PetriTransition(pn);
		t2.TransitionName = "T_e5";
		t2.InputPlaceName.add("P_x5");
		t2.InputPlaceName.add("P_TL3");

		Condition T2Ct1 = new Condition(t2, "P_TL3", TransitionCondition.Equal, "green");
		Condition T2Ct2 = new Condition(t2, "P_x5", TransitionCondition.HaveCar);
		T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition = T2Ct1;
		grdT2.Activations.add(new Activation(t2, "P_x5", TransitionOperation.PopElementWithoutTarget, "P_b5"));
	    grdT2.Activations.add(new Activation(t2, "P_TL3", TransitionOperation.Move, "P_TL3"));
	    
		t2.GuardMappingList.add(grdT2);

//		t2.Delay = 3;
		pn.Transitions.add(t2);

		// T3 ------------------------ Input LANE 2 -------------------------------------------------------------------------------------
		PetriTransition t3 = new PetriTransition(pn);
		t3.TransitionName = "T_u6";
		t3.InputPlaceName.add("P_a6");
		t3.InputPlaceName.add("P_x6");

		Condition T3Ct1 = new Condition(t3, "P_a6", TransitionCondition.NotNull);
		Condition T3Ct2 = new Condition(t3, "P_x6", TransitionCondition.CanAddCars);
		T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

		GuardMapping grdT3 = new GuardMapping();
		grdT3.condition = T3Ct1;
		grdT3.Activations.add(new Activation(t3, "P_a6", TransitionOperation.AddElement, "P_x6"));
		t3.GuardMappingList.add(grdT3);

		t3.Delay = 0;
		pn.Transitions.add(t3);

		// T4 ------------------------ Traffic Light LANE 2 ------------------------------------------------
		PetriTransition t4 = new PetriTransition(pn);
		t4.TransitionName = "T_e6";
		t4.InputPlaceName.add("P_x6");
		t4.InputPlaceName.add("P_TL4");

		Condition T4Ct1 = new Condition(t4, "P_TL4", TransitionCondition.Equal, "green");
		Condition T4Ct2 = new Condition(t4, "P_x6", TransitionCondition.HaveCar);
		T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

		GuardMapping grdT4 = new GuardMapping();
		grdT4.condition = T4Ct1;
		grdT4.Activations.add(new Activation(t4, "P_x6", TransitionOperation.PopElementWithoutTarget, "P_b6"));
		grdT4.Activations.add(new Activation(t4, "P_TL4", TransitionOperation.Move, "P_TL4"));
		t4.GuardMappingList.add(grdT2);

		t4.Delay = 0;
		pn.Transitions.add(t4);

		// T9----------------------------------------------------------------

		PetriTransition t9 = new PetriTransition(pn);
		t9.TransitionName = "T_g5Exit";
		t9.InputPlaceName.add("P_o5");

		Condition T9Ct1 = new Condition(t9, "P_o5", TransitionCondition.HaveCar);

		GuardMapping grdT9 = new GuardMapping();
		grdT9.condition = T9Ct1;
		grdT9.Activations.add(new Activation(t9, "P_o5", TransitionOperation.SendOverNetwork, "P_o5Exit"));
		t9.GuardMappingList.add(grdT9);

		t9.Delay = 0;
		pn.Transitions.add(t9);

		// T10----------------------------------------------------------------

		PetriTransition t10 = new PetriTransition(pn);
		t10.TransitionName = "T_g6Exit";
		t10.InputPlaceName.add("P_o6");

		Condition T10Ct1 = new Condition(t10, "P_o6", TransitionCondition.HaveCar);

		GuardMapping grdT10 = new GuardMapping();
		grdT10.condition = T10Ct1;
		grdT10.Activations.add(new Activation(t10, "P_o6", TransitionOperation.PopElementWithoutTarget, "P_o6Exit"));
		t10.GuardMappingList.add(grdT10);

		t10.Delay = 0;
		pn.Transitions.add(t10);

		// T11----------------------------------------------------------------

		PetriTransition t11 = new PetriTransition(pn);
		t11.TransitionName = "T_g7Exit";
		t11.InputPlaceName.add("P_o7");

		Condition T11Ct1 = new Condition(t11, "P_o7", TransitionCondition.HaveCar);

		GuardMapping grdT11 = new GuardMapping();
		grdT11.condition = T11Ct1;
		grdT11.Activations.add(new Activation(t11, "P_o7", TransitionOperation.PopElementWithoutTarget, "P_o7Exit"));
		t11.GuardMappingList.add(grdT11);

		t11.Delay = 0;
		pn.Transitions.add(t11);

		// --------------------------------------firstpart-------------------------------------------

		// T13 ------------------------------------------------
		PetriTransition t13 = new PetriTransition(pn);
		t13.TransitionName = "T_i5";
		t13.InputPlaceName.add("P_b5");
		t13.InputPlaceName.add("P_I");

		Condition T13Ct1 = new Condition(t13, "P_b5", TransitionCondition.NotNull);
		Condition T13Ct2 = new Condition(t13, "P_I", TransitionCondition.CanAddCars);
		T13Ct1.SetNextCondition(LogicConnector.AND, T13Ct2);

		GuardMapping grdT13 = new GuardMapping();
		grdT13.condition = T13Ct1;
		grdT13.Activations.add(new Activation(t13, "P_b5", TransitionOperation.AddElement, "P_I"));
		t13.GuardMappingList.add(grdT13);

		t13.Delay = 0;
		pn.Transitions.add(t13);

		// T14-----------------------------------------------------------
		PetriTransition t14 = new PetriTransition(pn);
		t14.TransitionName = "T_g5";
		t14.InputPlaceName.add("P_I");
		t14.InputPlaceName.add("P_o5");

		Condition T14Ct1 = new Condition(t14, "P_I", TransitionCondition.HaveCarForMe);
		Condition T14Ct2 = new Condition(t14, "P_o5", TransitionCondition.CanAddCars);
		T14Ct1.SetNextCondition(LogicConnector.AND, T14Ct2);

		GuardMapping grdT14 = new GuardMapping();
		grdT14.condition = T14Ct1;
		grdT14.Activations.add(new Activation(t14, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o5"));
		t14.GuardMappingList.add(grdT14);

		t14.Delay = 0;
		pn.Transitions.add(t14);

		// ---------------------------------SecondPart-------------------------------------------

		// T15 ------------------------------------------------
		PetriTransition t15 = new PetriTransition(pn);
		t15.TransitionName = "T_i6";
		t15.InputPlaceName.add("P_b6");
		t15.InputPlaceName.add("P_I");

		Condition T15Ct1 = new Condition(t15, "P_b6", TransitionCondition.NotNull);
		Condition T15Ct2 = new Condition(t15, "P_I", TransitionCondition.CanAddCars);
		T15Ct1.SetNextCondition(LogicConnector.AND, T15Ct2);

		GuardMapping grdT15 = new GuardMapping();
		grdT15.condition = T15Ct1;
		grdT15.Activations.add(new Activation(t15, "P_b6", TransitionOperation.AddElement, "P_I"));
		t15.GuardMappingList.add(grdT15);

		t15.Delay = 0;
		pn.Transitions.add(t15);

		// T16-----------------------------------------------------------
		PetriTransition t16 = new PetriTransition(pn);
		t16.TransitionName = "T_g6";
		t16.InputPlaceName.add("P_I");
		t16.InputPlaceName.add("P_o6");

		Condition T16Ct1 = new Condition(t16, "P_I", TransitionCondition.HaveCarForMe);
		Condition T16Ct2 = new Condition(t16, "P_o6", TransitionCondition.CanAddCars);
		T16Ct1.SetNextCondition(LogicConnector.AND, T16Ct2);

		GuardMapping grdT16 = new GuardMapping();
		grdT16.condition = T16Ct1;
		grdT16.Activations.add(new Activation(t16, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o6"));
		t16.GuardMappingList.add(grdT16);

		t16.Delay = 0;
		pn.Transitions.add(t16);

		// ----------------------ThirdPart----------------------------------------------------------------

		// T18---------------------------------------------------------

		PetriTransition t18 = new PetriTransition(pn);
		t18.TransitionName = "T_g7";
		t18.InputPlaceName.add("P_I");
		t18.InputPlaceName.add("P_o7");

		Condition T18Ct1 = new Condition(t18, "P_I", TransitionCondition.HaveCarForMe);
		Condition T18Ct2 = new Condition(t18, "P_o7", TransitionCondition.CanAddCars);
		T18Ct1.SetNextCondition(LogicConnector.AND, T18Ct2);

		GuardMapping grdT18 = new GuardMapping();
		grdT18.condition = T18Ct1;
		grdT18.Activations.add(new Activation(t18, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o7"));
		t18.GuardMappingList.add(grdT18);

		t18.Delay = 0;
		pn.Transitions.add(t18);

		// -------------------------------------------------------------------------------------
		// ----------------------------PNStart-------------------------------------------------
		// -------------------------------------------------------------------------------------

		System.out.println("Exp1 started \n ------------------------------");
		pn.Delay = 2000;
		// pn.Start();

		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);
	}
}
