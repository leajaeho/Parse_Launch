package pe.kr.crasy.parse_launch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by crasy on 15. 11. 18.
 */
public class SettingActivity extends PreferenceActivity {
    SharedPreferences getUpdateAllLaunchOnlyWIFIPreference;
    SharedPreferences getUpdateLaunchOnlyWIFIPreference;
    SharedPreferences getShowNotificationPreference;
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenceFragmentActivity()).commit();
        PreferenceManager.setDefaultValues(this, R.xml.preference, false);
    }
    public class PreferenceFragmentActivity extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(final Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
            addPreferencesFromResource(R.xml.preference);
            getSharedPreferences("dsf", MODE_PRIVATE);
            findPreference("JSoup").setOnPreferenceClickListener(this);
            findPreference("Realm").setOnPreferenceClickListener(this);
            findPreference("ThisApp").setOnPreferenceClickListener(this);
            findPreference("com.getbase:floatingactionbutton").setOnPreferenceClickListener(this);
            findPreference("materialdesignicons").setOnPreferenceClickListener(this);
            findPreference("UpdateAllLaunchOnlyWIFI").setOnPreferenceChangeListener(this);
            findPreference("UpdateLaunchOnlyWIFI").setOnPreferenceChangeListener(this);
            findPreference("ShowNotification").setOnPreferenceChangeListener(this);
            getUpdateAllLaunchOnlyWIFIPreference = getSharedPreferences("UpdateAllLaunchOnlyWIFI",MODE_PRIVATE);
            getUpdateLaunchOnlyWIFIPreference = getSharedPreferences("UpdateLaunchOnlyWIFI", MODE_PRIVATE);
            getShowNotificationPreference = getSharedPreferences("ShowNotification", MODE_PRIVATE);

        }
        @Override
        public boolean onPreferenceClick(Preference preference) {
            Intent intent = new Intent(SettingActivity.this,License.class);;
            switch (preference.getKey()){
                case "JSoup":
                    intent.putExtra("License", "JSoup");
                    break;
                case "Realm":
                    intent.putExtra("License", "Realm");
                    break;
                case "ThisApp":
                    intent.putExtra("License", "ThisApp");
                    break;
                case "com.getbase:floatingactionbutton":
                    intent.putExtra("License", "com.getbase:floatingactionbutton");
                    break;
                case "materialdesignicons":
                    intent.putExtra("License", "materialdesignicons");
                    break;
            }
            startActivity(intent);
            return false;
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            switch (preference.getKey()){
                case "UpdateAllLaunchOnlyWIFI":
                    Log.d("dd", "wifi1"+(Boolean)newValue);
                    getUpdateAllLaunchOnlyWIFIPreference.edit().putBoolean("UpdateAllLaunchOnlyWIFI", (Boolean) newValue);
                    break;
                case "UpdateLaunchOnlyWIFI":
                    Log.d("dd","wifi2"+newValue.toString());
                    break;
                case "ShowNotification":
                    Log.d("dd","mofoto"+newValue.toString());
                    break;
            }
            return true;
        }
    }
}
