package com.virtualsoft.core.service.chain

import com.virtualsoft.core.designpatterns.builder.IBuild
import com.virtualsoft.core.designpatterns.chain.IChain
import com.virtualsoft.core.service.IService

interface IServiceChain<T: IService>: IChain<T>, IBuild