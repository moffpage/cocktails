package kz.grandera.vlifetesttaskapp.core.componentcontext

import com.arkivanov.decompose.GenericComponentContext

import kz.grandera.vlifetesttaskapp.core.scope.ParentScopeIdOwner

public interface AppComponentContext : GenericComponentContext<AppComponentContext>, ParentScopeIdOwner