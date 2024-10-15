package kz.grandera.vlifetesttaskapp.core.scope

import org.koin.core.scope.ScopeID

public interface ParentScopeIdOwner {
    public val parentScopeId: ScopeID?
}