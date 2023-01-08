package project.session3;

import com.Components.*;
import com.DataObjects.DataString;
import com.Enumerations.LogicConnector;
import com.Enumerations.TransitionCondition;
import com.Enumerations.TransitionOperation;

public class Monitor {
    public static void main(String[] args) {

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Monitor";
        pn.NetworkPort = 1081;


        DataString p1r = new DataString();
        p1r.SetName("p1r");
        p1r.SetValue("monitor");
        pn.PlaceList.add(p1r);

        DataString p3r = new DataString();
        p3r.SetName("p3r");
        pn.PlaceList.add(p3r);

        DataString p2r = new DataString();
        p2r.SetName("p2r");
        pn.PlaceList.add(p2r);



//        --------------------------------- Transition 1 ----------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("p1r");
        t1.InputPlaceName.add("p3r");

        Condition T1Ct1 = new Condition(t1, "p3r", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "p1r", TransitionCondition.NotNull);

        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        grdT1.Activations.add(new Activation(t1,"p3r", TransitionOperation.Move, "p2r"));

        t1.GuardMappingList.add(grdT1);

        t1.Delay = 0;
        pn.Transitions.add(t1);

        //        --------------------------------- Transition 2 ----------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "T2";
        t2.InputPlaceName.add("p2r");

        Condition T2Ct1 = new Condition(t2, "p2r", TransitionCondition.NotNull);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        grdT2.Activations.add(new Activation(t2,"p2r", TransitionOperation.Move, "p1r"));

        t2.GuardMappingList.add(grdT2);

        t2.Delay = 0;
        pn.Transitions.add(t2);


        //-------------------------------------------------------------------------------------
        //----------------------------PN Start-------------------------------------------------
        //-------------------------------------------------------------------------------------

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 2000;
        //pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
