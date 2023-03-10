//package twointersectionsproject;
//
//import com.Components.Activation;
//import com.Components.Condition;
//import com.Components.GuardMapping;
//import com.Components.PetriNet;
//import com.Components.PetriNetWindow;
//import com.Components.PetriTransition;
//import com.DataObjects.DataCar;
//import com.DataObjects.DataCarQueue;
//import com.DataObjects.DataString;
//import com.DataObjects.DataTransfer;
//import com.DataOnly.TransferOperation;
//import com.Enumerations.LogicConnector;
//import com.Enumerations.TransitionCondition;
//import com.Enumerations.TransitionOperation;
//
//public class Intersections12 {
//    public static void main(String[] args) {
//
//        PetriNet pn = new PetriNet();
//        pn.PetriNetName = "Intersection 1";
//
//        pn.NetworkPort = 1081;
//
//        DataString green = new DataString();
//        green.Printable = false;
//        green.SetName("green");
//        green.SetValue("green");
//        pn.ConstantPlaceList.add(green);
//
//        // -------------------------------------------------------------------
//        // -------------------------------Lane1--------------------------------
//        // --------------------------------------------------------------------
//
//        DataCar p1 = new DataCar();
//        p1.SetName("P_a1");
//        pn.PlaceList.add(p1);
//
//        DataCarQueue p2 = new DataCarQueue();
//        p2.Value.Size = 3;
//        p2.SetName("P_x1");
//        pn.PlaceList.add(p2);
//
//        DataString p3 = new DataString();
//        p3.SetName("P_TL1");
//        pn.PlaceList.add(p3);
//
//        DataCar p4 = new DataCar();
//        p4.SetName("P_b1");
//        pn.PlaceList.add(p4);
//
//        // -------------------------------------------------------------------------------------
//        // --------------------------------Lane2-----------------------------------------------
//        // -------------------------------------------------------------------------------------
//
//        DataCar p5 = new DataCar(); //p5.Printable = false;
//        p5.SetName("P_a2");
//        pn.PlaceList.add(p5);
//
//        DataCarQueue p6 = new DataCarQueue(); //p6.Printable = false;
//        p6.Value.Size = 3;
//        p6.SetName("P_x2");
//        pn.PlaceList.add(p6);
//
//        DataString p7 = new DataString(); //p7.Printable = false;
//        p7.SetName("P_TL2");
//        pn.PlaceList.add(p7);
//
//        DataCar p8 = new DataCar(); //p8.Printable = false;
//        p8.SetName("P_b2");
//        pn.PlaceList.add(p8);
//
//        // ----------------------------------------------------------------------------
//        // ----------------------------Exit lane 1-------------------------------------
//        // ----------------------------------------------------------------------------
//
//        DataCarQueue p17 = new DataCarQueue(); //p17.Printable = false;
//        p17.Value.Size = 3;
//        p17.SetName("P_o1");
//        pn.PlaceList.add(p17);
//
//        DataCar p18 = new DataCar(); //p18.Printable = false;
//        p18.SetName("P_o1Exit");
//        pn.PlaceList.add(p18);
//
//        // ----------------------------------------------------------------------------
//        // ----------------------------Exit lane 2-------------------------------------
//        // ----------------------------------------------------------------------------
//
//        DataCarQueue p19 = new DataCarQueue(); //p19.Printable = false;
//        p19.Value.Size = 3;
//        p19.SetName("P_o2");
//        pn.PlaceList.add(p19);
//
//        DataTransfer p20 = new DataTransfer(); //p20.Printable = false;
//        p20.SetName("P_o2Exit");
//        p20.Value = new TransferOperation("localhost","1082", "P_a1" );
//        pn.PlaceList.add(p20);
//
//
//
//        // ----------------------------------------------------------------------------
//        // ----------------------------Exit lane 3-------------------------------------
//        // ----------------------------------------------------------------------------
//
//        DataCarQueue p21 = new DataCarQueue(); //p21.Printable = false;
//        p21.Value.Size = 3;
//        p21.SetName("P_o3");
//        pn.PlaceList.add(p21);
//
//        DataCar p22 = new DataCar(); //p22.Printable = false;
//        p22.SetName("P_o3Exit");
//        pn.PlaceList.add(p22);
//
//        // ----------------------------------------------------------------------------
//        // ----------------------------Exit lane 4-------------------------------------
//        // ----------------------------------------------------------------------------
//
//        DataCarQueue p23 = new DataCarQueue();
//        p23.Value.Size = 3;
//        p23.SetName("P_o4");
//        pn.PlaceList.add(p23);
//
//        DataCar p24 = new DataCar();
//        p24.SetName("P_o4Exit");
//        pn.PlaceList.add(p24);
//
//        // -------------------------------------------------------------------------------------------
//        // --------------------------------Intersection-----------------------------------------------
//        // -------------------------------------------------------------------------------------------
//
//        DataCarQueue p25 = new DataCarQueue();
//        p25.Value.Size = 3;
//        p25.SetName("P_I");
//        pn.PlaceList.add(p25);
//
//        // T1 --------------------------------------------------------
//        PetriTransition t1 = new PetriTransition(pn);
//        t1.TransitionName = "T_u1";
//        t1.InputPlaceName.add("P_a1");
//        t1.InputPlaceName.add("P_x1");
//
//        Condition T1Ct1 = new Condition(t1, "P_a1", TransitionCondition.NotNull);
//        Condition T1Ct2 = new Condition(t1, "P_x1", TransitionCondition.CanAddCars);
//        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);
//
//        GuardMapping grdT1 = new GuardMapping();
//        grdT1.condition = T1Ct1;
//        grdT1.Activations.add(new Activation(t1, "P_a1", TransitionOperation.AddElement, "P_x1"));
//        t1.GuardMappingList.add(grdT1);
//
//        t1.Delay = 0;
//        pn.Transitions.add(t1);
//
//        // T2 ------------------------------------------------
//        PetriTransition t2 = new PetriTransition(pn);
//        t2.TransitionName = "T_e1";
//        t2.InputPlaceName.add("P_x1");
//        t2.InputPlaceName.add("P_TL1");
//
//        Condition T2Ct1 = new Condition(t2, "P_TL1", TransitionCondition.Equal, "green");
//        Condition T2Ct2 = new Condition(t2, "P_x1", TransitionCondition.HaveCar);
//        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);
//
//        GuardMapping grdT2 = new GuardMapping();
//        grdT2.condition = T2Ct1;
//        grdT2.Activations.add(new Activation(t2, "P_x1", TransitionOperation.PopElementWithoutTarget, "P_b1"));
//        grdT2.Activations.add(new Activation(t2, "P_TL1", TransitionOperation.Move, "P_TL1"));
//
//        t2.GuardMappingList.add(grdT2);
//
////		t2.Delay = 3;
//        pn.Transitions.add(t2);
//
//        // T3 ------------------------------------------------
//        PetriTransition t3 = new PetriTransition(pn);
//        t3.TransitionName = "T_u2";
//        t3.InputPlaceName.add("P_a2");
//        t3.InputPlaceName.add("P_x2");
//
//        Condition T3Ct1 = new Condition(t3, "P_a2", TransitionCondition.NotNull);
//        Condition T3Ct2 = new Condition(t3, "P_x2", TransitionCondition.CanAddCars);
//        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);
//
//        GuardMapping grdT3 = new GuardMapping();
//        grdT3.condition = T3Ct1;
//        grdT3.Activations.add(new Activation(t3, "P_a2", TransitionOperation.AddElement, "P_x2"));
//        t3.GuardMappingList.add(grdT3);
//
//        t3.Delay = 0;
//        pn.Transitions.add(t3);
//
//        // T4 ------------------------------------------------
//        PetriTransition t4 = new PetriTransition(pn);
//        t4.TransitionName = "T_e2";
//        t4.InputPlaceName.add("P_x2");
//        t4.InputPlaceName.add("P_TL2");
//
//        Condition T4Ct1 = new Condition(t4, "P_TL2", TransitionCondition.Equal, "green");
//        Condition T4Ct2 = new Condition(t4, "P_x2", TransitionCondition.HaveCar);
//        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);
//
//        GuardMapping grdT4 = new GuardMapping();
//        grdT4.condition = T4Ct1;
//        grdT4.Activations.add(new Activation(t4, "P_x2", TransitionOperation.PopElementWithoutTarget, "P_b2"));
//        grdT4.Activations.add(new Activation(t4, "P_TL2", TransitionOperation.Move, "P_TL2"));
//        t4.GuardMappingList.add(grdT2);
//
//        t4.Delay = 0;
//        pn.Transitions.add(t4);
//
//        // T9----------------------------------------------------------------
//
//        PetriTransition t9 = new PetriTransition(pn);
//        t9.TransitionName = "T_g1Exit";
//        t9.InputPlaceName.add("P_o1");
//
//        Condition T9Ct1 = new Condition(t9, "P_o1", TransitionCondition.HaveCar);
//
//        GuardMapping grdT9 = new GuardMapping();
//        grdT9.condition = T9Ct1;
//        grdT9.Activations.add(new Activation(t9, "P_o1", TransitionOperation.PopElementWithoutTarget, "P_o1Exit"));
//        t9.GuardMappingList.add(grdT9);
//
//        t9.Delay = 0;
//        pn.Transitions.add(t9);
//
//        // T10----------------------------------------------------------------
//
//        PetriTransition t10 = new PetriTransition(pn);
//        t10.TransitionName = "T_g2Exit";
//        t10.InputPlaceName.add("P_o2");
//
//        Condition T10Ct1 = new Condition(t10, "P_o2", TransitionCondition.HaveCar);
//
//        GuardMapping grdT10 = new GuardMapping();
//        grdT10.condition = T10Ct1;
//        grdT10.Activations.add(new Activation(t10, "P_o2", TransitionOperation.PopElementWithoutTarget, "P_o2Exit"));
//        grdT10.Activations.add(new Activation(t10, "P_o2", TransitionOperation.SendOverNetwork, "P_o2Exit"));
//        t10.GuardMappingList.add(grdT10);
//
//        t10.Delay = 0;
//        pn.Transitions.add(t10);
//
//        // T11----------------------------------------------------------------
//
//        PetriTransition t11 = new PetriTransition(pn);
//        t11.TransitionName = "T_g3Exit";
//        t11.InputPlaceName.add("P_o3");
//
//        Condition T11Ct1 = new Condition(t11, "P_o3", TransitionCondition.HaveCar);
//
//        GuardMapping grdT11 = new GuardMapping();
//        grdT11.condition = T11Ct1;
//        grdT11.Activations.add(new Activation(t11, "P_o3", TransitionOperation.PopElementWithoutTarget, "P_o3Exit"));
//        t11.GuardMappingList.add(grdT11);
//
//        t11.Delay = 0;
//        pn.Transitions.add(t11);
//
//        // T12----------------------------------------------------------------
//
//        PetriTransition t12 = new PetriTransition(pn);
//        t12.TransitionName = "T_g4Exit";
//        t12.InputPlaceName.add("P_o4");
//
//        Condition T12Ct1 = new Condition(t12, "P_o4", TransitionCondition.HaveCar);
//
//        GuardMapping grdT12 = new GuardMapping();
//        grdT12.condition = T12Ct1;
//        grdT12.Activations.add(new Activation(t12, "P_o4", TransitionOperation.PopElementWithoutTarget, "P_o4Exit"));
//        t12.GuardMappingList.add(grdT12);
//
//        t12.Delay = 0;
//        pn.Transitions.add(t12);
//
//        // --------------------------------------firstpart-------------------------------------------
//
//        // T13 ------------------------------------------------
//        PetriTransition t13 = new PetriTransition(pn);
//        t13.TransitionName = "T_i1";
//        t13.InputPlaceName.add("P_b1");
//        t13.InputPlaceName.add("P_I");
//
//        Condition T13Ct1 = new Condition(t13, "P_b1", TransitionCondition.NotNull);
//        Condition T13Ct2 = new Condition(t13, "P_I", TransitionCondition.CanAddCars);
//        T13Ct1.SetNextCondition(LogicConnector.AND, T13Ct2);
//
//        GuardMapping grdT13 = new GuardMapping();
//        grdT13.condition = T13Ct1;
//        grdT13.Activations.add(new Activation(t13, "P_b1", TransitionOperation.AddElement, "P_I"));
//        t13.GuardMappingList.add(grdT13);
//
//        t13.Delay = 0;
//        pn.Transitions.add(t13);
//
//        // T14-----------------------------------------------------------
//        PetriTransition t14 = new PetriTransition(pn);
//        t14.TransitionName = "T_g1";
//        t14.InputPlaceName.add("P_I");
//        t14.InputPlaceName.add("P_o1");
//
//        Condition T14Ct1 = new Condition(t14, "P_I", TransitionCondition.HaveCarForMe);
//        Condition T14Ct2 = new Condition(t14, "P_o1", TransitionCondition.CanAddCars);
//        T14Ct1.SetNextCondition(LogicConnector.AND, T14Ct2);
//
//        GuardMapping grdT14 = new GuardMapping();
//        grdT14.condition = T14Ct1;
//        grdT14.Activations.add(new Activation(t14, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o1"));
//        t14.GuardMappingList.add(grdT14);
//
//        t14.Delay = 0;
//        pn.Transitions.add(t14);
//
//        // ---------------------------------SecondPart-------------------------------------------
//
//        // T15 ------------------------------------------------
//        PetriTransition t15 = new PetriTransition(pn);
//        t15.TransitionName = "T_i2";
//        t15.InputPlaceName.add("P_b2");
//        t15.InputPlaceName.add("P_I");
//
//        Condition T15Ct1 = new Condition(t15, "P_b2", TransitionCondition.NotNull);
//        Condition T15Ct2 = new Condition(t15, "P_I", TransitionCondition.CanAddCars);
//        T15Ct1.SetNextCondition(LogicConnector.AND, T15Ct2);
//
//        GuardMapping grdT15 = new GuardMapping();
//        grdT15.condition = T15Ct1;
//        grdT15.Activations.add(new Activation(t15, "P_b2", TransitionOperation.AddElement, "P_I"));
//        t15.GuardMappingList.add(grdT15);
//
//        t15.Delay = 0;
//        pn.Transitions.add(t15);
//
//        // T16-----------------------------------------------------------
//        PetriTransition t16 = new PetriTransition(pn);
//        t16.TransitionName = "T_g2";
//        t16.InputPlaceName.add("P_I");
//        t16.InputPlaceName.add("P_o2");
//
//        Condition T16Ct1 = new Condition(t16, "P_I", TransitionCondition.HaveCarForMe);
//        Condition T16Ct2 = new Condition(t16, "P_o2", TransitionCondition.CanAddCars);
//        T16Ct1.SetNextCondition(LogicConnector.AND, T16Ct2);
//
//        GuardMapping grdT16 = new GuardMapping();
//        grdT16.condition = T16Ct1;
//        grdT16.Activations.add(new Activation(t16, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o2"));
//        t16.GuardMappingList.add(grdT16);
//
//        t16.Delay = 0;
//        pn.Transitions.add(t16);
//
//        // ----------------------ThirdPart---------------------------------------------------------------
//
//        // T18---------------------------------------------------------
//
//        PetriTransition t18 = new PetriTransition(pn);
//        t18.TransitionName = "T_g3";
//        t18.InputPlaceName.add("P_I");
//        t18.InputPlaceName.add("P_o3");
//
//        Condition T18Ct1 = new Condition(t18, "P_I", TransitionCondition.HaveCarForMe);
//        Condition T18Ct2 = new Condition(t18, "P_o3", TransitionCondition.CanAddCars);
//        T18Ct1.SetNextCondition(LogicConnector.AND, T18Ct2);
//
//        GuardMapping grdT18 = new GuardMapping();
//        grdT18.condition = T18Ct1;
//        grdT18.Activations.add(new Activation(t18, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o3"));
//        t18.GuardMappingList.add(grdT18);
//
//        t18.Delay = 0;
//        pn.Transitions.add(t18);
//
//        // -------------------------------------FourthPart-----------------------------------------
//        // T20---------------------------------------------------------
//
//        PetriTransition t20 = new PetriTransition(pn);
//        t20.TransitionName = "T_g4";
//        t20.InputPlaceName.add("P_I");
//        t20.InputPlaceName.add("P_o4");
//
//        Condition T20Ct1 = new Condition(t20, "P_I", TransitionCondition.HaveCarForMe);
//        Condition T20Ct2 = new Condition(t20, "P_o4", TransitionCondition.CanAddCars);
//        T20Ct1.SetNextCondition(LogicConnector.AND, T20Ct2);
//
//        GuardMapping grdT20 = new GuardMapping();
//        grdT20.condition = T20Ct1;
//        grdT20.Activations.add(new Activation(t20, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o4"));
//        t20.GuardMappingList.add(grdT20);
//
//        t20.Delay = 0;
//        pn.Transitions.add(t20);
//
//
////        INTERSECTION 2
//
//        // -------------------------------------------------------------------
//        // -------------------------------Lane1--------------------------------
//        // --------------------------------------------------------------------
//
//        DataCar p11 = new DataCar();
//        p11.SetName("P_a5");
//        pn.PlaceList.add(p1);
//
//        DataCarQueue p21 = new DataCarQueue();
//        p21.Value.Size = 3;
//        p21.SetName("P_x5");
//        pn.PlaceList.add(p21);
//
//        DataString p31 = new DataString();
//        p31.SetName("P_TL3");
//        pn.PlaceList.add(p31);
//
//        DataCar p41 = new DataCar();
//        p41.SetName("P_b5");
//        pn.PlaceList.add(p41);
//
//        // -------------------------------------------------------------------------------------
//        // --------------------------------Lane2-----------------------------------------------
//        // -------------------------------------------------------------------------------------
//
//        DataCar p51 = new DataCar(); //p5.Printable = false;
//        p51.SetName("P_a6");
//        pn.PlaceList.add(p51);
//
//        DataCarQueue p61 = new DataCarQueue(); //p6.Printable = false;
//        p61.Value.Size = 3;
//        p61.SetName("P_x6");
//        pn.PlaceList.add(p61);
//
//        DataString p71 = new DataString(); //p7.Printable = false;
//        p71.SetName("P_TL4");
//        pn.PlaceList.add(p71);
//
//        DataCar p81 = new DataCar(); //p8.Printable = false;
//        p81.SetName("P_b6");
//        pn.PlaceList.add(p81);
//
//
//        // ----------------------------------------------------------------------------
//        // ----------------------------Exit lane 1-------------------------------------
//        // ----------------------------------------------------------------------------
//
//        DataCarQueue p171 = new DataCarQueue(); //p17.Printable = false;
//        p171.Value.Size = 3;
//        p171.SetName("P_o5");
//        pn.PlaceList.add(p171);
//
//        DataTransfer p181 = new DataTransfer(); //p18.Printable = false;
//        p181.SetName("P_o5Exit");
//        p181.Value = new TransferOperation("localhost","1081", "P_a1" );
//        pn.PlaceList.add(p181);
//
//        // ----------------------------------------------------------------------------
//        // ----------------------------Exit lane 2-------------------------------------
//        // ----------------------------------------------------------------------------
//
//        DataCarQueue p191 = new DataCarQueue(); //p19.Printable = false;
//        p191.Value.Size = 3;
//        p191.SetName("P_o6");
//        pn.PlaceList.add(p191);
//
//        DataCar p201 = new DataCar(); //p20.Printable = false;
//        p201.SetName("P_o6Exit");
//        pn.PlaceList.add(p201);
//
//        // ----------------------------------------------------------------------------
//        // ----------------------------Exit lane 3-------------------------------------
//        // ----------------------------------------------------------------------------
//
//        DataCarQueue p211 = new DataCarQueue(); //p21.Printable = false;
//        p211.Value.Size = 3;
//        p211.SetName("P_o7");
//        pn.PlaceList.add(p211);
//
//        DataCar p221 = new DataCar(); //p22.Printable = false;
//        p221.SetName("P_o7Exit");
//        pn.PlaceList.add(p221);
//
//
//        // -------------------------------------------------------------------------------------------
//        // --------------------------------Intersection-----------------------------------------------
//        // -------------------------------------------------------------------------------------------
//
//        DataCarQueue p251 = new DataCarQueue();
//        p251.Value.Size = 3;
//        p251.SetName("P_I");
//        pn.PlaceList.add(p251);
//
//        // T1 ------------------------ Input LANE 1 ------------------------------------------------
//        PetriTransition t111 = new PetriTransition(pn);
//        t11.TransitionName = "T_u5";
//        t11.InputPlaceName.add("P_a5");
//        t11.InputPlaceName.add("P_x5");
//
//        Condition T1Ct11 = new Condition(t1, "P_a5", TransitionCondition.NotNull);
//        Condition T1Ct21 = new Condition(t1, "P_x5", TransitionCondition.CanAddCars);
//        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct21);
//
//        GuardMapping grdT11 = new GuardMapping();
//        grdT11.condition = T1Ct11;
//        grdT11.Activations.add(new Activation(t1, "P_a5", TransitionOperation.AddElement, "P_x5"));
//        t111.GuardMappingList.add(grdT11);
//
//        t111.Delay = 0;
//        pn.Transitions.add(t111);
//
//        // T2 ------------------------ Traffic Light LANE 1 ------------------------------------------------
//        PetriTransition t2 = new PetriTransition(pn);
//        t212.TransitionName = "T_e5";
//        t212.InputPlaceName.add("P_x5");
//        t212.InputPlaceName.add("P_TL3");
//
//        Condition T2Ct11 = new Condition(t212, "P_TL3", TransitionCondition.Equal, "green");
//        Condition T2Ct21 = new Condition(t212, "P_x5", TransitionCondition.HaveCar);
//        T2Ct11.SetNextCondition(LogicConnector.AND, T2Ct21);
//
//        GuardMapping grdT2 = new GuardMapping();
//        grdT2.condition = T2Ct1;
//        grdT2.Activations.add(new Activation(t212, "P_x5", TransitionOperation.PopElementWithoutTarget, "P_b5"));
//        grdT2.Activations.add(new Activation(t212, "P_TL3", TransitionOperation.Move, "P_TL3"));
//
//        t212.GuardMappingList.add(grdT2);
//
////		t2.Delay = 3;
//        pn.Transitions.add(t212);
//
//        // T3 ------------------------ Input LANE 2 -------------------------------------------------------------------------------------
//        PetriTransition t3 = new PetriTransition(pn);
//        t3.TransitionName = "T_u6";
//        t3.InputPlaceName.add("P_a6");
//        t3.InputPlaceName.add("P_x6");
//
//        Condition T3Ct1 = new Condition(t3, "P_a6", TransitionCondition.NotNull);
//        Condition T3Ct2 = new Condition(t3, "P_x6", TransitionCondition.CanAddCars);
//        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);
//
//        GuardMapping grdT3 = new GuardMapping();
//        grdT3.condition = T3Ct1;
//        grdT3.Activations.add(new Activation(t3, "P_a6", TransitionOperation.AddElement, "P_x6"));
//        t3.GuardMappingList.add(grdT3);
//
//        t3.Delay = 0;
//        pn.Transitions.add(t3);
//
//        // T4 ------------------------ Traffic Light LANE 2 ------------------------------------------------
//        PetriTransition t4 = new PetriTransition(pn);
//        t4.TransitionName = "T_e6";
//        t4.InputPlaceName.add("P_x6");
//        t4.InputPlaceName.add("P_TL4");
//
//        Condition T4Ct1 = new Condition(t4, "P_TL4", TransitionCondition.Equal, "green");
//        Condition T4Ct2 = new Condition(t4, "P_x6", TransitionCondition.HaveCar);
//        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);
//
//        GuardMapping grdT4 = new GuardMapping();
//        grdT4.condition = T4Ct1;
//        grdT4.Activations.add(new Activation(t4, "P_x6", TransitionOperation.PopElementWithoutTarget, "P_b6"));
//        grdT4.Activations.add(new Activation(t4, "P_TL4", TransitionOperation.Move, "P_TL4"));
//        t4.GuardMappingList.add(grdT2);
//
//        t4.Delay = 0;
//        pn.Transitions.add(t4);
//
//        // T9----------------------------------------------------------------
//
//        PetriTransition t9 = new PetriTransition(pn);
//        t9.TransitionName = "T_g5Exit";
//        t9.InputPlaceName.add("P_o5");
//
//        Condition T9Ct1 = new Condition(t9, "P_o5", TransitionCondition.HaveCar);
//
//        GuardMapping grdT9 = new GuardMapping();
//        grdT9.condition = T9Ct1;
//        grdT9.Activations.add(new Activation(t9, "P_o5", TransitionOperation.SendOverNetwork, "P_o5Exit"));
//        t9.GuardMappingList.add(grdT9);
//
//        t9.Delay = 0;
//        pn.Transitions.add(t9);
//
//        // T10----------------------------------------------------------------
//
//        PetriTransition t10 = new PetriTransition(pn);
//        t10.TransitionName = "T_g6Exit";
//        t10.InputPlaceName.add("P_o6");
//
//        Condition T10Ct1 = new Condition(t10, "P_o6", TransitionCondition.HaveCar);
//
//        GuardMapping grdT10 = new GuardMapping();
//        grdT10.condition = T10Ct1;
//        grdT10.Activations.add(new Activation(t10, "P_o6", TransitionOperation.PopElementWithoutTarget, "P_o6Exit"));
//        t10.GuardMappingList.add(grdT10);
//
//        t10.Delay = 0;
//        pn.Transitions.add(t10);
//
//        // T11----------------------------------------------------------------
//
//        PetriTransition t11 = new PetriTransition(pn);
//        t11.TransitionName = "T_g7Exit";
//        t11.InputPlaceName.add("P_o7");
//
//        Condition T11Ct1 = new Condition(t11, "P_o7", TransitionCondition.HaveCar);
//
//        GuardMapping grdT11 = new GuardMapping();
//        grdT11.condition = T11Ct1;
//        grdT11.Activations.add(new Activation(t11, "P_o7", TransitionOperation.PopElementWithoutTarget, "P_o7Exit"));
//        t11.GuardMappingList.add(grdT11);
//
//        t11.Delay = 0;
//        pn.Transitions.add(t11);
//
//        // --------------------------------------firstpart-------------------------------------------
//
//        // T13 ------------------------------------------------
//        PetriTransition t13 = new PetriTransition(pn);
//        t13.TransitionName = "T_i5";
//        t13.InputPlaceName.add("P_b5");
//        t13.InputPlaceName.add("P_I");
//
//        Condition T13Ct1 = new Condition(t13, "P_b5", TransitionCondition.NotNull);
//        Condition T13Ct2 = new Condition(t13, "P_I", TransitionCondition.CanAddCars);
//        T13Ct1.SetNextCondition(LogicConnector.AND, T13Ct2);
//
//        GuardMapping grdT13 = new GuardMapping();
//        grdT13.condition = T13Ct1;
//        grdT13.Activations.add(new Activation(t13, "P_b5", TransitionOperation.AddElement, "P_I"));
//        t13.GuardMappingList.add(grdT13);
//
//        t13.Delay = 0;
//        pn.Transitions.add(t13);
//
//        // T14-----------------------------------------------------------
//        PetriTransition t14 = new PetriTransition(pn);
//        t14.TransitionName = "T_g5";
//        t14.InputPlaceName.add("P_I");
//        t14.InputPlaceName.add("P_o5");
//
//        Condition T14Ct1 = new Condition(t14, "P_I", TransitionCondition.HaveCarForMe);
//        Condition T14Ct2 = new Condition(t14, "P_o5", TransitionCondition.CanAddCars);
//        T14Ct1.SetNextCondition(LogicConnector.AND, T14Ct2);
//
//        GuardMapping grdT14 = new GuardMapping();
//        grdT14.condition = T14Ct1;
//        grdT14.Activations.add(new Activation(t14, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o5"));
//        t14.GuardMappingList.add(grdT14);
//
//        t14.Delay = 0;
//        pn.Transitions.add(t14);
//
//        // ---------------------------------SecondPart-------------------------------------------
//
//        // T15 ------------------------------------------------
//        PetriTransition t15 = new PetriTransition(pn);
//        t15.TransitionName = "T_i6";
//        t15.InputPlaceName.add("P_b6");
//        t15.InputPlaceName.add("P_I");
//
//        Condition T15Ct1 = new Condition(t15, "P_b6", TransitionCondition.NotNull);
//        Condition T15Ct2 = new Condition(t15, "P_I", TransitionCondition.CanAddCars);
//        T15Ct1.SetNextCondition(LogicConnector.AND, T15Ct2);
//
//        GuardMapping grdT15 = new GuardMapping();
//        grdT15.condition = T15Ct1;
//        grdT15.Activations.add(new Activation(t15, "P_b6", TransitionOperation.AddElement, "P_I"));
//        t15.GuardMappingList.add(grdT15);
//
//        t15.Delay = 0;
//        pn.Transitions.add(t15);
//
//        // T16-----------------------------------------------------------
//        PetriTransition t16 = new PetriTransition(pn);
//        t16.TransitionName = "T_g6";
//        t16.InputPlaceName.add("P_I");
//        t16.InputPlaceName.add("P_o6");
//
//        Condition T16Ct1 = new Condition(t16, "P_I", TransitionCondition.HaveCarForMe);
//        Condition T16Ct2 = new Condition(t16, "P_o6", TransitionCondition.CanAddCars);
//        T16Ct1.SetNextCondition(LogicConnector.AND, T16Ct2);
//
//        GuardMapping grdT16 = new GuardMapping();
//        grdT16.condition = T16Ct1;
//        grdT16.Activations.add(new Activation(t16, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o6"));
//        t16.GuardMappingList.add(grdT16);
//
//        t16.Delay = 0;
//        pn.Transitions.add(t16);
//
//        // ----------------------ThirdPart----------------------------------------------------------------
//
//        // T18---------------------------------------------------------
//
//        PetriTransition t18 = new PetriTransition(pn);
//        t18.TransitionName = "T_g7";
//        t18.InputPlaceName.add("P_I");
//        t18.InputPlaceName.add("P_o7");
//
//        Condition T18Ct1 = new Condition(t18, "P_I", TransitionCondition.HaveCarForMe);
//        Condition T18Ct2 = new Condition(t18, "P_o7", TransitionCondition.CanAddCars);
//        T18Ct1.SetNextCondition(LogicConnector.AND, T18Ct2);
//
//        GuardMapping grdT18 = new GuardMapping();
//        grdT18.condition = T18Ct1;
//        grdT18.Activations.add(new Activation(t18, "P_I", TransitionOperation.PopElementWithTargetToQueue, "P_o7"));
//        t18.GuardMappingList.add(grdT18);
//
//        t18.Delay = 0;
//        pn.Transitions.add(t18);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        // -------------------------------------------------------------------------------------
//        // ----------------------------PNStart-------------------------------------------------
//        // -------------------------------------------------------------------------------------
//
//        System.out.println("Exp1 started \n ------------------------------");
//        pn.Delay = 2000;
//        // pn.Start();
//
//
//
//        PetriNetWindow frame = new PetriNetWindow(false);
//        frame.petriNet = pn;
//        frame.setVisible(true);
//    }
//}
