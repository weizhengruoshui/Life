package com.stephen.combination.dagger.scope

import javax.inject.Scope

/**
 * use for object which life circle is same as fragment
 */

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope