package com.virtualsoft.core.service.chain

import com.virtualsoft.core.designpatterns.builder.IBuilder
import com.virtualsoft.core.service.IService

interface IServiceChainBuilder<T : IService> : IBuilder<IServiceChain<T>>