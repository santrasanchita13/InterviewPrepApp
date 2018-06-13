package com.santra.sanchita.interviewprepapp.data;

import com.santra.sanchita.interviewprepapp.data.db.DbHelper;
import com.santra.sanchita.interviewprepapp.data.network.ApiHelper;
import com.santra.sanchita.interviewprepapp.data.prefs.PreferenceHelper;

import io.reactivex.Observable;

/**
 * Created by sanchita on 20/11/17.
 */

public interface DataManager extends DbHelper, ApiHelper, PreferenceHelper {
}
