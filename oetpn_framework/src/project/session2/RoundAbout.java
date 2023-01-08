package project.session2;

import com.Components.*;
import com.DataObjects.DataCar;
import com.DataObjects.DataCarQueue;
import com.Enumerations.LogicConnector;
import com.Enumerations.TransitionCondition;
import com.Enumerations.TransitionOperation;

public class RoundAbout {

    public static void main(String[] args) {

        PetriNet roundAbout = new PetriNet();
        roundAbout.PetriNetName = "Round About Intersection";
        roundAbout.NetworkPort = 1082;


        DataCar p1 = new DataCar();
        p1.SetName("p1");
        roundAbout.PlaceList.add(p1);

        DataCarQueue p2 = new DataCarQueue();
        p2.SetName("p2");
        roundAbout.PlaceList.add(p2);

        DataCarQueue p3 = new DataCarQueue();
        p3.SetName("p3");
        roundAbout.PlaceList.add(p3);

        DataCarQueue p4 = new DataCarQueue();
        p4.SetName("p4");
        roundAbout.PlaceList.add(p4);


        DataCar p5 = new DataCar();
        p5.SetName("p5");
        roundAbout.PlaceList.add(p5);

        DataCar p6 = new DataCar();
        p6.SetName("p6");
        roundAbout.PlaceList.add(p6);

        DataCar p7 = new DataCar();
        p7.SetName("p7");
        roundAbout.PlaceList.add(p7);

        DataCar p8 = new DataCar();
        p8.SetName("p8");
        roundAbout.PlaceList.add(p8);


        // -------------------------- Transitions 1------------------------

        PetriTransition t1 = new PetriTransition(roundAbout);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p1");

        Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "p2", TransitionCondition.CanAddCars);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        grdT1.Activations.add(new Activation(t1, "p1", TransitionOperation.AddElement, "p2"));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        roundAbout.Transitions.add(t1);


        // -------------------------- Transitions 1 EXIT------------------------

        PetriTransition t1Exit = new PetriTransition(roundAbout);
        t1Exit.TransitionName = "t1Exit";
        t1Exit.InputPlaceName.add("p2");

        Condition T1ExitCt1 = new Condition(t1Exit, "p2", TransitionCondition.HaveCarForMe);

        GuardMapping grdT1Exit = new GuardMapping();
        grdT1Exit.condition = T1ExitCt1;

        grdT1Exit.Activations.add(new Activation(t1Exit, "p2", TransitionOperation.PopElementWithTarget, "p6"));

        t1Exit.GuardMappingList.add(grdT1Exit);
        t1Exit.Delay = 0;
        roundAbout.Transitions.add(t1Exit);

        // -------------------------- Transitions 2------------------------

        PetriTransition t2 = new PetriTransition(roundAbout);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p2");

        Condition T2Ct1 = new Condition(t2, "p2", TransitionCondition.HaveCarForMe);
        Condition T2Ct2 = new Condition(t2, "p3", TransitionCondition.CanAddCars);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        grdT2.Activations.add(new Activation(t2, "p2", TransitionOperation.PopElementWithTargetToQueue, "p3"));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        roundAbout.Transitions.add(t2);

        // -------------------------- Transitions 2 EXIT------------------------

        PetriTransition t2Exit = new PetriTransition(roundAbout);
        t2Exit.TransitionName = "t2Exit";
        t2Exit.InputPlaceName.add("p3");

        Condition T2ExitCt1 = new Condition(t2Exit, "p3", TransitionCondition.HaveCarForMe);

        GuardMapping grdT2Exit = new GuardMapping();
        grdT2Exit.condition = T2ExitCt1;

        grdT2Exit.Activations.add(new Activation(t2Exit, "p3", TransitionOperation.PopElementWithTarget, "p7"));

        t2Exit.GuardMappingList.add(grdT2Exit);
        t2Exit.Delay = 0;
        roundAbout.Transitions.add(t2Exit);


        // -------------------------- Transitions 3------------------------

        PetriTransition t3 = new PetriTransition(roundAbout);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p3");

        Condition T3Ct1 = new Condition(t3, "p3", TransitionCondition.HaveCarForMe);
        Condition T3Ct2 = new Condition(t3, "p4", TransitionCondition.CanAddCars);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;

        grdT3.Activations.add(new Activation(t3, "p3", TransitionOperation.PopElementWithTargetToQueue, "p4"));

        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        roundAbout.Transitions.add(t3);


        // -------------------------- Transitions 3 EXIT------------------------

        PetriTransition t3Exit = new PetriTransition(roundAbout);
        t3Exit.TransitionName = "t3Exit";
        t3Exit.InputPlaceName.add("p4");

        Condition T3ExitCt1 = new Condition(t3Exit, "p4", TransitionCondition.HaveCarForMe);

        GuardMapping grdT3Exit = new GuardMapping();
        grdT3Exit.condition = T3ExitCt1;

        grdT3Exit.Activations.add(new Activation(t3Exit, "p4", TransitionOperation.PopElementWithTarget, "p8"));

        t3Exit.GuardMappingList.add(grdT3Exit);
        t3Exit.Delay = 0;
        roundAbout.Transitions.add(t3Exit);


        // -------------------------- Transitions 4 ------------------------

        PetriTransition t4 = new PetriTransition(roundAbout);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("p4");

        Condition T4Ct1 = new Condition(t4, "p4", TransitionCondition.HaveCarForMe);
        Condition T4Ct2 = new Condition(t4, "p2", TransitionCondition.CanAddCars);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;

        grdT4.Activations.add(new Activation(t4, "p4", TransitionOperation.PopElementWithTargetToQueue, "p2"));

        t4.GuardMappingList.add(grdT4);
        t4.Delay = 0;
        roundAbout.Transitions.add(t4);


        // -------------------------- Transitions 3 EXIT------------------------

        PetriTransition t4Exit = new PetriTransition(roundAbout);
        t4Exit.TransitionName = "t4Exit";
        t4Exit.InputPlaceName.add("p2");

        Condition T4ExitCt1 = new Condition(t4Exit, "p2", TransitionCondition.HaveCarForMe);

        GuardMapping grdT4Exit = new GuardMapping();
        grdT4Exit.condition = T4ExitCt1;

        grdT4Exit.Activations.add(new Activation(t4Exit, "p2", TransitionOperation.PopElementWithTarget, "p5"));

        t4Exit.GuardMappingList.add(grdT4Exit);
        t4Exit.Delay = 0;
        roundAbout.Transitions.add(t4Exit);



        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = roundAbout;
        frame.setVisible(true);


        // t2,t2Exit
    }

}
