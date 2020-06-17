package com.virtualsoft.core.designpatterns.chain

import com.virtualsoft.core.designpatterns.factory.IFactory
import com.virtualsoft.core.designpatterns.factory.IProduct

interface IChainFactory<T: IProduct> : IFactory<T> {

    fun isProducer(request: IChainRequest): Boolean
}