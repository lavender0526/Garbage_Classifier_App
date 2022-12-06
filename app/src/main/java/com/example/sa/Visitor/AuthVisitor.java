package com.example.sa.Visitor;

public interface AuthVisitor {
     void visit(RedgistMoneyMiddleware redgistMoneyMiddleware);
     void visit(ConnectCanMiddleware connectCanMiddleware);
     void visit(RegistLocationMiddleware registLocationMiddleware);
     void visit(RedgistrReviseAccountMiddleware redgistrReviseAccountMiddleware);
     void visit(RegistTrashcanMiddleware registTrashcanMiddleware);
}
