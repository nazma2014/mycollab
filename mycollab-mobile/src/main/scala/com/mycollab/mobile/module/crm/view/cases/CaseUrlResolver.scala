/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
  * This file is part of mycollab-mobile.
  *
  * mycollab-mobile is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * mycollab-mobile is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
  */
package com.mycollab.mobile.module.crm.view.cases

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.crm.events.{CaseEvent, CrmEvent}
import com.mycollab.mobile.module.crm.{CrmModuleScreenData, CrmUrlResolver}
import com.mycollab.module.crm.domain.Account

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class CaseUrlResolver extends CrmUrlResolver {
  this.addSubResolver("list", new CaseListUrlResolver)
  this.addSubResolver("add", new CaseAddUrlResolver)
  this.addSubResolver("edit", new CaseEditUrlResolver)
  this.addSubResolver("preview", new CasePreviewUrlResolver)

  class CaseListUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CrmEvent.GotoActivitiesView(this,
        new CrmModuleScreenData.GotoModule(Array())))
    }
  }

  class CaseAddUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new CaseEvent.GotoAdd(this, new Account))
    }
  }

  class CaseEditUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val caseId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new CaseEvent.GotoEdit(this, caseId))
    }
  }

  class CasePreviewUrlResolver extends CrmUrlResolver {
    protected override def handlePage(params: String*) {
      val caseId = UrlTokenizer(params(0)).getInt
      EventBusFactory.getInstance().post(new CaseEvent.GotoRead(this, caseId))
    }
  }

}
