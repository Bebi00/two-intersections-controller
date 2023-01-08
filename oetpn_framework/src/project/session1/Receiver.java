package project.session1;

import com.Components.*;
import com.DataObjects.DataFloat;
import com.Enumerations.LogicConnector;
import com.Enumerations.TransitionCondition;
import com.Enumerations.TransitionOperation;

import java.util.ArrayList;

public class Receiver {

    public static void main(String[] args) {


        PetriNet receiverPetriNet = new PetriNet();
        receiverPetriNet.PetriNetName = "Receiver Petri";
        receiverPetriNet.NetworkPort = 1081;

        DataFloat pR1 = new DataFloat();
        pR1.SetName("PR1");
        receiverPetriNet.PlaceList.add(pR1);

        DataFloat pR2 = new DataFloat();
        pR2.SetName("PR2");
        receiverPetriNet.PlaceList.add(pR2);
        pR2.SetValue(1.0F);

        DataFloat pR3 = new DataFloat();
        pR3.SetName("PR3");
        receiverPetriNet.PlaceList.add(pR3);

        DataFloat pR4 = new DataFloat();
        pR4.SetName("PR4");
        receiverPetriNet.PlaceList.add(pR4);

        PetriTransition tr1 = new PetriTransition(receiverPetriNet);
        tr1.TransitionName = "tr1";
        tr1.InputPlaceName.add("PR1");
        tr1.InputPlaceName.add("PR2");

        Condition TR1Ct1 = new Condition(tr1, "PR1", TransitionCondition.NotNull);
        Condition TR1Ct2 = new Condition(tr1, "PR2", TransitionCondition.NotNull);
        TR1Ct1.SetNextCondition(LogicConnector.AND, TR1Ct2);

        GuardMapping grdTR1 = new GuardMapping();
        grdTR1.condition = TR1Ct1;
        ArrayList<String> inputPlacesTR1 = new ArrayList<>();
        inputPlacesTR1.add(pR1.GetName());
        inputPlacesTR1.add(pR1.GetName());

        grdTR1.Activations.add(new Activation(tr1, inputPlacesTR1, TransitionOperation.Add, "PR3"));

        tr1.GuardMappingList.add(grdTR1);
        tr1.Delay = 0;
        receiverPetriNet.Transitions.add(tr1);


        PetriTransition tr2 = new PetriTransition(receiverPetriNet);
        tr2.TransitionName = "tr2";
        tr2.InputPlaceName.add("PR3");

        Condition TR2Ct1 = new Condition(tr2, "PR3", TransitionCondition.NotNull);

        GuardMapping grdTR2 = new GuardMapping();
        grdTR2.condition = TR2Ct1;
        grdTR2.Activations.add(new Activation(tr2, "PR3", TransitionOperation.Move, "PR2"));

        tr2.GuardMappingList.add(grdTR2);
        tr2.Delay = 0;
        receiverPetriNet.Transitions.add(tr2);


        PetriNetWindow frame2 = new PetriNetWindow(false);
        frame2.petriNet = receiverPetriNet;
        frame2.setVisible(true);
    }
}
