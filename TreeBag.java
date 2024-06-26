public class TreeBag<E extends Comparable> implements Cloneable {
   private BTNode<E> root;

   public void add(E newGolfer) {
      if (this.root == null) {
         this.root = new BTNode(newGolfer, (BTNode)null, (BTNode)null);
      } else {
         BTNode current = this.root;

         while(current != null) {
            if (newGolfer.compareTo(current.getData()) == 0) {
               return;
            }

            if (newGolfer.compareTo(current.getData()) < 0) {
               if (current.getLeft() == null) {
                  current.setLeft(new BTNode(newGolfer, (BTNode)null, (BTNode)null));
                  return;
               }

               current = current.getLeft();
            } else {
               if (current.getRight() == null) {
                  current.setRight(new BTNode(newGolfer, (BTNode)null, (BTNode)null));
                  return;
               }

               current = current.getRight();
            }
         }

      }
   }

   public E retrieve(E target) {
      BTNode current = this.root;

      while(current != null) {
         int cmp = target.compareTo(current.getData());
         if (cmp == 0) {
            return (Comparable)current.getData();
         }

         if (cmp < 0) {
            current = current.getLeft();
         } else {
            current = current.getRight();
         }
      }

      return null;
   }

   public boolean remove(E data) {
      if (this.root == null) {
         return false;
      } else {
         BTNode<E> current = this.root;
         BTNode<E> parent = null;
         boolean isLeftChild = true;

         while(current != null && !((Comparable)current.getData()).equals(data)) {
            parent = current;
            if (data.equals(current.getData())) {
               current = current.getLeft();
               isLeftChild = true;
            } else {
               current = current.getRight();
               isLeftChild = false;
            }
         }

         if (current == null) {
            return false;
         } else if (current.getLeft() == null && current.getRight() == null) {
            if (current == this.root) {
               this.root = null;
            } else if (isLeftChild) {
               parent.setLeft((BTNode)null);
            } else {
               parent.setRight((BTNode)null);
            }

            return true;
         } else if (current.getRight() == null) {
            if (current == this.root) {
               this.root = current.getLeft();
            } else if (isLeftChild) {
               parent.setLeft(current.getLeft());
            } else {
               parent.setRight(current.getLeft());
            }

            return true;
         } else if (current.getLeft() == null) {
            if (current == this.root) {
               this.root = current.getRight();
            } else if (isLeftChild) {
               parent.setLeft(current.getRight());
            } else {
               parent.setRight(current.getRight());
            }

            return true;
         } else {
            BTNode<E> heirParent = current;

            BTNode heir;
            for(heir = current.getRight(); heir.getLeft() != null; heir = heir.getLeft()) {
               heirParent = heir;
            }

            if (heirParent != current) {
               heirParent.setLeft(heir.getRight());
               heir.setRight(current.getRight());
            }

            if (current == this.root) {
               this.root = heir;
            } else if (isLeftChild) {
               parent.setLeft(heir);
            } else {
               parent.setRight(heir);
            }

            heir.setLeft(current.getLeft());
            return true;
         }
      }
   }

   public void display() {
      if (this.root == null) {
         throw new IllegalArgumentException("The tree is empty");
      } else {
         this.root.inorderPrint();
      }
   }

   public void displayAsTree() {
      if (this.root == null) {
         throw new IllegalArgumentException("The tree is empty");
      } else {
         this.root.print(0);
      }
   }

   public TreeBag<E> clone() {
      return null;
   }

   public int countOccurrences(E target) {
      return 0;
   }

   public int size() {
      return BTNode.treeSize(this.root);
   }

   public void addAll(TreeBag<E> addend) {
   }

   public static TreeBag union(TreeBag b1, TreeBag b2) {
      return null;
   }
}
