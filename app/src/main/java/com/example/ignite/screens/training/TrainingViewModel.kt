package com.example.ignite.screens.training

import com.example.ignite.IgniteViewModel
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor(
    val accountService: AccountService,
    logService: LogService
) : IgniteViewModel(logService){

}