/**
 * Copyright (c) 1998-2001 Oded Nissan.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */


package onjlib.utils;
import java.util.Stack;
import java.util.Vector;
import java.util.Comparator;

/**
 * Btree class
 * A generic binary tree class.
 * @author Oded Nissan mailto:odedns@geocities.com
 * @version 1.00 1/10/1998.
 */
 public class Btree {

	Comparator m_comp;
	BtreeNode m_rootNode;
	int m_traverseType;
	int m_nelem;

	/**
	 *  constructor for Btree class.
	 *  @param c Comparator object.
	 */
	public Btree(Comparator c)
	{
		m_comp = c;
		m_rootNode = null;
		m_nelem = 0;
			//{{INIT_CONTROLS
		//}}
}

	/**
	 * constructor initialize the object with another Btree object.
	 * @param t a Btree object
	 */
	public Btree(Btree t)
	{
		m_comp = t.m_comp;
		m_rootNode = t.m_rootNode;
		m_nelem = t.m_nelem;
	}

	/**
	 * check if Btree has more elements.
	 * @return - true if List has more elements, false otherwise.
	 **/
	public boolean hasMoreElements()
	{
		return(m_rootNode != null);
	}


	/**
	 * insert elements into the Btree at the root node.
	 * @param o Object to insert into the Btree.
	 */
	public void insertElement(Object o)
	{
		m_rootNode = addElement(m_rootNode,o);
		m_nelem++;

	}

	/**
	 * insert an object into the Btree at the node specified
	 * by node.
	 * @param node the node to insert the object under.
	 * @param o Object to add to tree.
	 */
	public BtreeNode addElement(BtreeNode node, Object o)
	{
		int val;

		if(null == node) {

			node = new BtreeNode();
			node.m_data = o;
			node.m_left = node.m_right = null;
			return(node);
		}

		val = m_comp.compare(o,node.m_data);

		/* o greater than node data
		 * search right subtree
		 */
		if(val > 0) {
			node.m_right = addElement(node.m_right,o);
		} else {
		/* o smaller than node data
		 * search left subtree
		 */
			if(val < 0) {
				node.m_left = addElement(node.m_left,o);
			} else {
			/* o equals  node data
			 * return
			 */
				//return(null);
			}
		} /* if */

		return(node);
	} /* addElement */


	/**
	 * search of an object in the Btree, return the Object.
	 * @param o Object to search for.
	 * @return the Object found, or null if not found.
	 */
	public Object search(Object o)
	{
		BtreeNode root = m_rootNode;
			return (null);

	}
	/**
	 * print the Binary tree
	 **/
	void printTree()
	{
		traverseTree(m_rootNode);
	   //	 sTree(m_rootNode);
	}

	/**
	 * traverse the Binary Tree
	 * @param root the root node of the tree to traverse.
	 */
	void traverseTree(BtreeNode root)
	{
		if(null == root) {
			return;
		}
		if(root.m_left != null) {
			traverseTree(root.m_left);
		}
		System.out.println("Object : " + root.m_data.toString());
		if(root.m_right!= null) {
			traverseTree(root.m_right);
		}
	}


	void sTree(BtreeNode root)
	{
		BtreeNode  n = root;
		Stack st = new Stack();

		st.push(root);
		n = n.m_left;
		while(false == st.empty()) {

			while(n != null) {
				System.out.println("pushing to stack");
				st.push(n);
				n = n.m_left;

				}
			n = (BtreeNode) st.pop();
			System.out.println("object : " + n.m_data.toString());
				n = n.m_right;
			}
		}


	/**
	 * create a Vector out of the Btree.
	 * @return Vector the created Vector.
	 */
	public Vector toVector()
	{
		Vector v = new Vector(m_nelem);

		FillVec(m_rootNode,v);
		return(v);
	}


	void FillVec(BtreeNode root, Vector v)
	{
		BtreeNode  n = root;
		Stack st = new Stack();

		st.push(root);
		n = n.m_left;
		while(false == st.empty()) {

			while(n != null) {
				st.push(n);
				n = n.m_left;

				}
			n = (BtreeNode) st.pop();
		v.addElement(n);
				n = n.m_right;
			}
		}

	//{{DECLARE_CONTROLS
	//}}
}

