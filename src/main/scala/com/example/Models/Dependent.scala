package com.example.Models

case class Dependent (
                       dependentId: Int,
                       referenceEmpId: Int,
                       dependentName: String,
                       relation: String,
                       age : Int
                     )
