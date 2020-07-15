package com.virtualsoft.core.service.database

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.virtualsoft.core.service.database.data.IDocument

interface IFirestoreDatabase {

    fun readDocument(documentReference: DocumentReference, callback: (IDocument?) -> Unit)

    fun readCollection(collectionReference: CollectionReference, callback: (List<IDocument>) -> Unit)

    fun readCollection(query: Query, callback: (List<IDocument>) -> Unit)

    fun writeDocument(documentReference: DocumentReference, data: IDocument, callback: ((Boolean) -> Unit)? = null)

    fun updateDocument(documentReference: DocumentReference, field: String, value: Any, callback: ((Boolean) -> Unit)? = null)

    fun deleteDocument(documentReference: DocumentReference, callback: ((Boolean) -> Unit)? = null)
}