package project.session1;

import com.Components.*;
import com.DataObjects.DataFloat;
import com.DataObjects.DataTransfer;
import com.DataOnly.TransferOperation;
import com.Enumerations.LogicConnector;
import com.Enumerations.TransitionCondition;
import com.Enumerations.TransitionOperation;

import java.util.ArrayList;

public class Sender {

    public static void main(String[] args) {
        PetriNet senderPetriNet = new PetriNet();
        senderPetriNet.PetriNetName = "Sender Petri";
        senderPetriNet.NetworkPort = 1080;


        DataFloat constantValue1 = new DataFloat();
        constantValue1.SetName("constantValue1");
        constantValue1.SetValue(0.0f);
        senderPetriNet.ConstantPlaceList.add(constantValue1);

        DataFloat constantValue2 = new DataFloat();
        constantValue2.SetName("constantValue2");
        constantValue2.SetValue(0.05f);
        senderPetriNet.ConstantPlaceList.add(constantValue2);

        DataFloat p1 = new DataFloat();
        p1.SetName("P1");
        senderPetriNet.PlaceList.add(p1);

        DataFloat p2 = new DataFloat();
        p2.SetName("P2");
        senderPetriNet.PlaceList.add(p2);
        p2.SetValue(1.0F);

        DataFloat p3 = new DataFloat();
        p3.SetName("P3");
        senderPetriNet.PlaceList.add(p3);

        DataFloat p4= new DataFloat();
        p4.SetName("P4");
        senderPetriNet.PlaceList.add(p4);

        DataTransfer p5 = new DataTransfer();
        p5.SetName("P5");
        p5.Value = new  TransferOperation("localhost", "1081", "PR1");
        senderPetriNet.PlaceList.add(p5);

        PetriTransition ts1 = new PetriTransition(senderPetriNet);
        ts1.TransitionName = "ts1";
        ts1.InputPlaceName.add("P1");
        ts1.InputPlaceName.add("P2");

        Condition TS1Ct1 = new Condition(ts1, "P1", TransitionCondition.MoreThan, "constantValue1");
        Condition TS1Ct2 = new Condition(ts1, "P2", TransitionCondition.NotNull);
        TS1Ct1.SetNextCondition(LogicConnector.AND, TS1Ct2);

        GuardMapping grdTS1 = new GuardMapping();
        grdTS1.condition = TS1Ct1;
        ArrayList<String> inputPlacesTS1 = new ArrayList<>();
        inputPlacesTS1.add(p1.GetName());
        inputPlacesTS1.add(p2.GetName());

        grdTS1.Activations.add(new Activation(ts1, inputPlacesTS1, TransitionOperation.Add, "P3"));

        ts1.GuardMappingList.add(grdTS1);
        ts1.Delay = 0;
        senderPetriNet.Transitions.add(ts1);



        PetriTransition ts2 = new PetriTransition(senderPetriNet);
        ts2.TransitionName = "ts2";
        ts2.InputPlaceName.add("P3");

        Condition TS2Ct1 = new Condition(ts2, "P3", TransitionCondition.NotNull);

        GuardMapping grdTS2 = new GuardMapping();
        grdTS2.condition = TS2Ct1;
        ArrayList<String> inputPlacesTS2 = new ArrayList<>();
        inputPlacesTS2.add(p3.GetName());
        inputPlacesTS2.add(constantValue2.GetName());
        grdTS2.Activations.add(new Activation(ts2, inputPlacesTS2, TransitionOperation.Prod, "P4"));

        ts2.GuardMappingList.add(grdTS2);
        ts2.Delay = 0;
        senderPetriNet.Transitions.add(ts2);



        PetriTransition ts3 = new PetriTransition(senderPetriNet);
        ts3.TransitionName = "ts3";
        ts3.InputPlaceName.add("P4");

        Condition TS3Ct1 = new Condition(ts3, "P4", TransitionCondition.NotNull);

        GuardMapping grdTS3 = new GuardMapping();
        grdTS3.condition = TS3Ct1;
        grdTS3.Activations.add(new Activation(ts3, "P4", TransitionOperation.SendOverNetwork, "P5"));
        grdTS3.Activations.add(new Activation(ts3, "P4", TransitionOperation.Move, "P2"));

        ts3.GuardMappingList.add(grdTS3);
        ts3.Delay = 0;
        senderPetriNet.Transitions.add(ts3);



        //--------------------------------------




        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = senderPetriNet;
        frame.setVisible(true);


    }


}
