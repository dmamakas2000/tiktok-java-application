package gr.aueb.cs.tiktokapplication.ui.addhashtag;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import gr.aueb.cs.tiktokapplication.R;

public class add_hashtag extends Fragment {

    private AddHashtagViewModel mViewModel;

    public static add_hashtag newInstance() {
        return new add_hashtag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<String> aa = getActivity().getIntent().getExtras().getStringArrayList("INFO");

        for (String e: aa) {
            System.out.println(e);
        }

        View root = inflater.inflate(R.layout.fragment_add_hashtag, container, false);
        TextView t = root.findViewById(R.id.channel_name_text_view);
        t.setText("MALAKA");

        return inflater.inflate(R.layout.fragment_add_hashtag, container, false);
    }

}