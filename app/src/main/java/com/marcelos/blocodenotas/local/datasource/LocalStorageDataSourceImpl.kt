package com.marcelos.blocodenotas.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.marcelos.blocodenotas.domain.datasource.LocalStorageDataSource
import com.marcelos.blocodenotas.utils.ANNOTATION_ORDER_BY_KEY
import com.marcelos.blocodenotas.utils.LOCAL_DATASTORE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
class LocalStorageDataSourceImpl(
    private val context: Context
) : LocalStorageDataSource {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = LOCAL_DATASTORE_NAME
    )

    private val annotationKey = stringPreferencesKey(ANNOTATION_ORDER_BY_KEY)

    override val key: Flow<String>
        get() = context.dataStore.data.map { localStorage ->
            localStorage[annotationKey] ?: ""
        }

    override suspend fun saveAnnotation(annotation: String) {
        context.dataStore.edit { localStorage ->
            localStorage[annotationKey] = annotation
        }
    }
}