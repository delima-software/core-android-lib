package com.virtualsoft.core.designpatterns.chain

import com.virtualsoft.core.designpatterns.factory.IProduct

interface IChain<T: IProduct> {

    fun resolve(request: IChainRequest): T?
}