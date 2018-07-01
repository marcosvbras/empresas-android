package com.marcosvbras.empresas.business.domain

data class Enterprise(
        var id: Int = 0,
        var value: Int = 0,
        var shares: Int = 0,
        var description: String = "",
        var facebook: String = "",
        var twitter: String = "",
        var linkedin: String = "",
        var phone: String = "",
        var photo: String = "",
        var city: String = "",
        var country: String = "",
        var enterpriseName: String = "",
        var emailEnterprise: String = "",
        var ownEnterprise: Boolean = false,
        var sharePrice: Int = 0,
        var ownShares: Int = 0,
        var enterpriseType: EnterpriseType
)