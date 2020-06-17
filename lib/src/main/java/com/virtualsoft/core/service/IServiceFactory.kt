package com.virtualsoft.core.service

import com.virtualsoft.core.designpatterns.chain.IChainFactory

interface IServiceFactory<T : IService> : IChainFactory<T>